package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Patterns {
    String[] value();
}