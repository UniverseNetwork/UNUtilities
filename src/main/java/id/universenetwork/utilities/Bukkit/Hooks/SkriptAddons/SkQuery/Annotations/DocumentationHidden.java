package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface DocumentationHidden {
}