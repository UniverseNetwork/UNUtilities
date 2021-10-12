package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.config.Config;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.OpenCloseable;
import com.google.common.collect.Lists;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.OptionsPragma;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.CancellableBukkitTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getScheduler;

@Name("Developer Mode Option")
@Description("Enable the developer mode pragma to auto reload a script as it changes.  This must be placed under the script-local options.")
@Examples("script options:;->$ developer mode")
@Patterns("$ developer mode")
public class EffOptionDeveloperMode extends OptionsPragma {
    long lastUpdated;

    @Override
    protected void register(final File executingScript, final SkriptParser.ParseResult parseResult) {
        lastUpdated = executingScript.lastModified();
        CancellableBukkitTask task = new CancellableBukkitTask() {
            @Override
            public void run() {
                if (lastUpdated != executingScript.lastModified()) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6SkQuery&7] &r(Dev Mode) Starting auto-reload of script '" + executingScript.getName() + "'"));
                    try {
                        Method unloadScript = ScriptLoader.class.getDeclaredMethod("unloadScript", File.class);
                        unloadScript.setAccessible(true);
                        unloadScript.invoke(null, executingScript);
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    Config script = ScriptLoader.loadStructure(executingScript);
                    ScriptLoader.loadScripts(Lists.newArrayList(script), OpenCloseable.EMPTY).join();
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&6SkQuery&7] &r(Dev Mode) '" + executingScript.getName() + "' has been reloaded."));
                    cancel();
                }
            }
        };
        task.setTaskId(getScheduler().scheduleSyncRepeatingTask(plugin, task, 0, 100));
    }
}