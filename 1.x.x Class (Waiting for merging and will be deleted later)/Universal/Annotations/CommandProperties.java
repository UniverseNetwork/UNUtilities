package id.universenetwork.utilities.Universal.Annotations;

@java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface CommandProperties {
    String Name();

    String Permission() default "";

    boolean PlayerOnly();

    String[] Aliases() default {};

    String Description() default "";
}