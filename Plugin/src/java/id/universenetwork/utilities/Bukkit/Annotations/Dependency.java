package id.universenetwork.utilities.Bukkit.Annotations;

@java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Dependency {
    String[] value();
}