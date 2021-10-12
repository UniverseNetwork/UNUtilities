package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.*;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.Pragma;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.IterableEnumeration;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static ch.njol.skript.Skript.registerExpression;
import static ch.njol.skript.lang.ExpressionType.PROPERTY;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Reflection.getCaller;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class Registration {
    public static void enableSnooper() {
        final Class<?> caller = getCaller();
        final URL callerLocation = caller.getProtectionDomain().getCodeSource().getLocation();
        File src;
        try {
            src = new File(callerLocation.toURI());
        } catch (URISyntaxException e) {
            src = new File(callerLocation.getPath());
        }
        try {
            PluginDescriptionFile desc = plugin.getPluginLoader().getPluginDescription(src);
            getLogger().info(prefix + " §eLocating classes ...");
            try {
                Set<Class<?>> classes = new HashSet<>();
                @SuppressWarnings("resource")
                JarFile jar = new JarFile(src);
                for (JarEntry e : new IterableEnumeration<>(jar.entries())) {
                    if (e.getName().endsWith(".class")) {
                        String className = e.getName().replace('/', '.').substring(0, e.getName().length() - 6);
                        try {
                            Class<?> c = Class.forName(className, false, caller.getClassLoader());
                            if (c == Pragma.class || c == OptionsPragma.class || c == AbstractTask.class) continue;
                            if (Effect.class.isAssignableFrom(c) || Condition.class.isAssignableFrom(c) || Expression.class.isAssignableFrom(c) || AbstractTask.class.isAssignableFrom(c))
                                classes.add(c);
                        } catch (ClassNotFoundException error) {
                            error.printStackTrace();
                        } catch (NoClassDefFoundError | ExceptionInInitializerError | IllegalAccessError ignored) {
                        }
                    }
                }
                register(desc, classes.toArray(new Class[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InvalidDescriptionException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    static void register(PluginDescriptionFile info, Class[] classes) {
        int success = 0;
        getLogger().info(prefix + " §eBeginning to process a total of " + classes.length);
        main:
        for (final Class c : classes) {
            Annotation[] annotations = c.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof Dependency) for (String s : ((Dependency) a).value())
                    if (getPluginManager().getPlugin(s) == null) continue main;
                if (a instanceof AntiDependency) for (String s : ((AntiDependency) a).value())
                    if (getPluginManager().getPlugin(s) != null) continue main;
                if (a instanceof Disabled) continue main;
            }
            if (Effect.class.isAssignableFrom(c)) {
                if (c.isAnnotationPresent(Patterns.class)) {
                    Skript.registerEffect(c, ((Patterns) c.getAnnotation(Patterns.class)).value());
                    Documentation.addEffect(c);
                    success++;
                } else
                    getLogger().severe(prefix + " §c" + c.getCanonicalName() + " is patternless and failed to register. This is most likely a code error.");
            } else if (Condition.class.isAssignableFrom(c)) {
                if (c.isAnnotationPresent(Patterns.class)) {
                    Skript.registerCondition(c, ((Patterns) c.getAnnotation(Patterns.class)).value());
                    Documentation.addCondition(c);
                    success++;
                } else if (!PropertyCondition.class.isAssignableFrom(c))
                    getLogger().severe(prefix + " §c" + c.getCanonicalName() + " is patternless and failed to register. This is most likely a code error.");
            } else if (Expression.class.isAssignableFrom(c)) {
                if (c.isAnnotationPresent(Patterns.class)) try {
                    Expression ex = (Expression) c.newInstance();
                    registerExpression(c, ex.getReturnType(), PROPERTY, ((Patterns) c.getAnnotation(Patterns.class)).value());
                    Documentation.addExpression(c);
                    success++;
                } catch (InstantiationException e) {
                    getLogger().severe(prefix + " §c" + c.getCanonicalName() + " could not be instantiated by SkQuery!");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                else if (c.isAnnotationPresent(UsePropertyPatterns.class) && c.isAnnotationPresent(PropertyFrom.class) && c.isAnnotationPresent(PropertyTo.class)) {
                    try {
                        Expression ex = (Expression) c.newInstance();
                        registerExpression(c, ex.getReturnType(), PROPERTY, "[the] " + Arrays.toString(((PropertyTo) c.getAnnotation(PropertyTo.class)).value()) + " o%" + Arrays.toString(((PropertyFrom) c.getAnnotation(PropertyFrom.class)).value()) + "%", "%" + Arrays.toString(((PropertyFrom) c.getAnnotation(PropertyFrom.class)).value()) + "%'[s] " + Arrays.toString(((PropertyTo) c.getAnnotation(PropertyTo.class)).value()));
                        Documentation.addExpression(c);
                        success++;
                    } catch (InstantiationException e) {
                        getLogger().severe(prefix + " §c" + c.getCanonicalName() + " could not be instantiated by SkQuery!");
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else
                    getLogger().severe(prefix + " §c" + c.getCanonicalName() + " is patternless and failed to register. This is most likely a code error.");
            } else if (AbstractTask.class.isAssignableFrom(c)) try {
                AbstractTask task = (AbstractTask) c.newInstance();
                task.run();
                success++;
            } catch (InstantiationException e) {
                getLogger().severe(prefix + " §c" + c.getCanonicalName() + " could not be instantiated by SkQuery!");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            else assert false;
        }
        getLogger().info(prefix + " §6Out of " + classes.length + " classes, §a" + success + " classes were loaded");
    }
}