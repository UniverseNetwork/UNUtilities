package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.YamlBuilder;
import id.universenetwork.utilities.Bukkit.Manager.API;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static id.universenetwork.utilities.Bukkit.Utils.Logger.info;

/**
 * UNUtilities - Bukkit
 */
public final class UNUtilities extends org.bukkit.plugin.java.JavaPlugin {
    public static UNUtilities plugin;
    public static YamlBuilder cfg;
    public static YamlBuilder data;
    public static String prefix;

    /**
     * Initialize config & data before plugin is enabled
     */
    @Override
    public void onLoad() {
        plugin = this;
        getLogger().info("§eInitializing Config & Data Manager...");
        try {
            cfg = new YamlBuilder("config.yml");
            data = new YamlBuilder("data.yml");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        prefix = translateColor(cfg.getString("Settings.prefix"));
        info("&aConfig & Data Manager has been initialized!");
    }

    @Override
    public void onEnable() {
        API.init();
        info("UNUtilities has been enabled!");
    }

    @Override
    public void onDisable() {
        API.declare();
        info("UNUtilities has been disabled!");
    }

    /**
     * Create a NamespacedKey without calling plugin instance
     */
    public static NamespacedKey createKey(String Key) {
        return new NamespacedKey(plugin, Key);
    }

    /**
     * Translate color codes to actual color using Bukkit API and replace %p% with prefix
     */
    public static String translateColor(String txt) {
        return org.apache.commons.lang.StringUtils.replace(org.bukkit.ChatColor.translateAlternateColorCodes('&', txt), "%p%", prefix);
    }

    /**
     * Reload config & data and calling Reload Event
     */
    public static void reloadCfg() {
        info("&eReloading Configuration & Data...");
        cfg.reload();
        data.reload();
        prefix = cfg.getString("Settings.prefix");
        info("&aConfiguration & Data has been reloaded!");
    }

    public static List<String> getOnlinePlayers(String partialName) {
        return filterStartingWith(partialName, Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName));
    }

    public static List<String> filterStartingWith(String prefix, Stream<String> stream) {
        return stream.filter(s -> s != null && !s.isEmpty() && s.toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
    }
}