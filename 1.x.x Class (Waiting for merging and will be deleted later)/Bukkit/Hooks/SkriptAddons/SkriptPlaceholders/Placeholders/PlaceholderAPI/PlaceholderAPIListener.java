package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderAPI;

import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderEvent;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.bukkit.UNUtilities.plugin;

public class PlaceholderAPIListener extends PlaceholderExpansion {
    final String prefix;

    public PlaceholderAPIListener(String prefix) {
        this.prefix = prefix;
    }

    @Override
    @NotNull
    public String getIdentifier() {
        return prefix;
    }

    @Override
    @NotNull
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(@Nullable OfflinePlayer player, @NotNull String identifier) {
        PlaceholderEvent event = new PlaceholderEvent(this.prefix + "_" + identifier, player);
        Bukkit.getPluginManager().callEvent(event);
        return event.getResult();
    }
}