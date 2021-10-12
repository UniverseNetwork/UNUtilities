package id.universenetwork.utilities.Bukkit.Tasks;

import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class CompatibilityCheckTask implements Runnable {
    final static String NAME = Bukkit.getServer().getClass().getPackage().getName();
    final static String VERSION = NAME.substring(NAME.lastIndexOf('.') + 1);
    final String[] supportedVersions = new String[]{"v1_14_R1", "v1_15_R1", "v1_16_R1", "v1_16_R2", "v1_16_R3"};
    boolean pass;

    public CompatibilityCheckTask() {
        run();
    }

    @Override
    public void run() {
        try {
            Class.forName("org.bukkit.craftbukkit." + VERSION + ".entity.memory.CraftMemoryMapper").getMethod("toNms", Object.class).invoke(null, (Object) null);
        } catch (InvocationTargetException e) {
            pass = false;
            warn("Villager Optimization Features need to be using a more recent build of Craftbukkit/Spigot/Paper!");
            return;
        } catch (IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            pass = false;
            warn("Villager Optimization Features is not compatible with the version of Minecraft you are using.");
            return;
        }
        for (String string : supportedVersions) {
            if (string.equals(VERSION)) {
                pass = true;
                break;
            }
            pass = false;
        }
        if (!pass)
            warn("Villager Optimization Features is not compatible with the version of Minecraft you are using.");
    }

    private void warn(String message) {
        Bukkit.getLogger().warning(prefix + " §6" + message);
    }

    public boolean passedCheck() {
        return pass;
    }
}