package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;

import java.io.IOException;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class SkriptPlaceholders {
    public static final boolean hasMVdW = Skript.classExists("be.maximvdw.placeholderapi.PlaceholderAPI");
    public static final boolean hasPapi = Skript.classExists("me.clip.placeholderapi.expansion.PlaceholderExpansion");

    public SkriptPlaceholders() {
        if (Enabled("SkriptPlaceholders")) {
            if (!hasPapi && !hasMVdW) {
                Bukkit.getLogger().warning(prefix + " §ePlaceholderAPI or MVdWPlaceholderAPI not found. §cYou need PlaceholderAPI or MVdWPlaceholderAPI to use §6SkriptPlaceholders §cAddon");
                return;
            }
            try {
                Skript.registerAddon(plugin).loadClasses("id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Skript.Elements");
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