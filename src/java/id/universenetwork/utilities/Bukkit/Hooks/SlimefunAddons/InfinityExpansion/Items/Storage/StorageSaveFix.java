package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static java.util.logging.Level.INFO;
import static org.bukkit.Bukkit.getLogger;

@lombok.experimental.UtilityClass
public final class StorageSaveFix {
    public static void fixStuff() {
        long time = System.nanoTime();
        int fixed = 0;
        File folder = new File("data-storage/Slimefun/stored-blocks/");
        String[] ids = new String[]{"INFINITY_STORAGE", "VOID_STORAGE", "REINFORCED_STORAGE", "ADVANCED_STORAGE", "BASIC_STORAGE"};
        for (File world : folder.listFiles()) {
            String name = world.getName();
            int locationBeginIndex = name.length() + 1;
            java.util.Map<String, String> locations = new java.util.HashMap<>();
            for (String id : ids) {
                File storages = new File(world, id + ".sfb");
                if (!storages.exists()) continue;
                java.util.List<String> lines;
                boolean changed = false;
                try {
                    lines = Files.readAllLines(storages.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
                java.util.Iterator<String> iterator = lines.listIterator();
                while (iterator.hasNext()) {
                    String line = iterator.next();
                    String location = line.substring(locationBeginIndex, line.indexOf(':'));
                    String correct = locations.get(location);
                    if (correct == null) locations.put(location, id);
                    else {
                        iterator.remove();
                        changed = true;
                        if (fixed++ < 25) {
                            String[] cords = io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns.SEMICOLON.split(location);
                            getLogger().log(INFO, prefix + " §fFixed bugged " + correct + " in " + name + " @ " + cords[0] + ", " + cords[1] + ", " + cords[2]);
                        }
                    }
                }
                if (changed) try {
                    Files.write(storages.toPath(), lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        time = System.nanoTime() - time;
        if (fixed > 0)
            getLogger().log(INFO, prefix + " §fFixed " + fixed + " duplicate storage(s) in " + (time / 1000000) + " ms");
    }
}