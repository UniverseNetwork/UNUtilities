package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderAPI;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkriptPlaceholders.Placeholders.PlaceholderEvent;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class PlaceholderAPIListener extends PlaceholderExpansion {
    final String prefix;

    public PlaceholderAPIListener(String prefix) {
        this.prefix = prefix;
    }

    @Override
    @NonNull
    public String getIdentifier() {
        return prefix;
    }

    @Override
    @NonNull
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    @NonNull
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(@Nullable OfflinePlayer player, @NonNull String identifier) {
        PlaceholderEvent event = new PlaceholderEvent(this.prefix + "_" + identifier, player);
        Bukkit.getPluginManager().callEvent(event);
        return event.getResult();
    }
}