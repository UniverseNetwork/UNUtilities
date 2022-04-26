package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.YamlBuilder;
import id.universenetwork.utilities.Bukkit.Manager.API;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Utils.Logger.info;

public final class UNUtilities extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener {
    public static UNUtilities plugin;
    public static YamlBuilder cfg;
    public static String prefix;

    @Override
    public void onLoad() {
        plugin = this;
        getLogger().info("§eInitializing Config Manager...");
        try {
            cfg = new YamlBuilder("config.yml");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        prefix = translateColor(cfg.getString("Settings.prefix"));
        info("UNUtilities has been loaded!");
    }

    @Override
    public void onEnable() {
        API.init();
        getServer().getPluginManager().registerEvents(this, this);
        info("UNUtilities has been enabled!");
    }

    @Override
    public void onDisable() {
        API.declare();
        info("UNUtilities has been disabled!");
    }

    public static NamespacedKey createKey(String Key) {
        return new NamespacedKey(plugin, Key);
    }

    public static String translateColor(String txt) {
        return org.apache.commons.lang.StringUtils.replace(org.bukkit.ChatColor.translateAlternateColorCodes('&', txt), "%p%", prefix);
    }

    @org.bukkit.event.EventHandler
    public void onConfigReload(id.universenetwork.utilities.Bukkit.Events.ReloadConfigEvent e) {
        prefix = cfg.getString("Settings.prefix");
    }
}