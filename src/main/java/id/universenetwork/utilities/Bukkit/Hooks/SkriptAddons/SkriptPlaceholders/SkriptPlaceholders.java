package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders;

import java.io.IOException;

import static ch.njol.skript.Skript.classExists;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public class SkriptPlaceholders {
    public static final boolean hasMVdW = classExists("be.maximvdw.placeholderapi.PlaceholderAPI");
    public static final boolean hasPapi = classExists("me.clip.placeholderapi.expansion.PlaceholderExpansion");

    public SkriptPlaceholders() {
        if (Enabled("SkriptPlaceholders")) {
            if (!hasPapi && !hasMVdW) {
                getLogger().warning(prefix + " §ePlaceholderAPI or MVdWPlaceholderAPI not found. §cYou need PlaceholderAPI or MVdWPlaceholderAPI to use §6SkriptPlaceholders §cAddon");
                return;
            }
            try {
                addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements");
                if (hasPapi && hasMVdW)
                    System.out.println(prefix + " §aPlaceholderAPI and MVdWPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
                else if (hasPapi && !hasMVdW)
                    System.out.println(prefix + " §aPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
                else if (!hasPapi && hasMVdW)
                    System.out.println(prefix + " §aMVdWPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}