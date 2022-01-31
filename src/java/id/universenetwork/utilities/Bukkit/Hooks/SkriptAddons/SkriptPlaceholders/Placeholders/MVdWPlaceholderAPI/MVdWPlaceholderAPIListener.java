package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.MVdWPlaceholderAPI;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderEvent;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class MVdWPlaceholderAPIListener {
    final String placeholder;

    public MVdWPlaceholderAPIListener(String placeholder) {
        this.placeholder = placeholder;
    }

    public void register() {
        PlaceholderAPI.registerPlaceholder(plugin, placeholder, e -> {
            PlaceholderEvent event = new PlaceholderEvent(placeholder, e.getPlayer());
            Bukkit.getPluginManager().callEvent(event);
            return event.getResult();
        });
    }
}