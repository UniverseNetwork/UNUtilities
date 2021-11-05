package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders;

import static ch.njol.skript.Skript.classExists;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public class SkriptPlaceholders {
    public static final boolean hasMVdW = classExists("be.maximvdw.placeholderapi.PlaceholderAPI");
    public static final boolean hasPAPI = classExists("me.clip.placeholderapi.expansion.PlaceholderExpansion");

    public SkriptPlaceholders() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled("SkriptPlaceholders")) {
            if (!hasPAPI && !hasMVdW) {
                getLogger().warning(prefix + " §ePlaceholderAPI or MVdWPlaceholderAPI not found. §cYou need PlaceholderAPI or MVdWPlaceholderAPI to use §6SkriptPlaceholders §cAddon");
                return;
            }
            try {
                id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements");
                if (hasPAPI && hasMVdW)
                    System.out.println(prefix + " §aPlaceholderAPI and MVdWPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
                else if (hasPAPI && !hasMVdW)
                    System.out.println(prefix + " §aPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
                else if (!hasPAPI && hasMVdW)
                    System.out.println(prefix + " §aMVdWPlaceholderAPI found. §bSuccessfully Registered §6SkriptPlaceholders §bAddon");
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }
}