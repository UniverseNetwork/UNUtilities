package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.QuarSK.Utils;

/**
 * Created by ARTHUR on 21/07/2017.
 */
public class ReflectionUtil {
    @org.jetbrains.annotations.Nullable
    public static Object getInstanceField(Object instance, String field) {
        Class<?> c = instance.getClass();
        try {
            java.lang.reflect.Field f = c.getDeclaredField(field);
            f.setAccessible(true);
            return f.get(instance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}