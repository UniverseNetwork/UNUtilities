package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons;

import static ch.njol.skript.Skript.classExists;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class SkriptPlaceholders extends Addons {
    public static final boolean hasMVdW = classExists("be.maximvdw.placeholderapi.PlaceholderAPI");
    public static final boolean hasPAPI = classExists("me.clip.placeholderapi.expansion.PlaceholderExpansion");

    public SkriptPlaceholders() {
        super("SkriptPlaceholders");
    }

    @Override
    public void Load() {
        if (!hasPAPI && !hasMVdW) {
            org.bukkit.Bukkit.getLogger().severe(prefix + " §ePlaceholderAPI or MVdWPlaceholderAPI not found. §cYou need PlaceholderAPI or MVdWPlaceholderAPI to use §6SkriptPlaceholders §cAddon");
            return;
        }
        try {
            addon.loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements");
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