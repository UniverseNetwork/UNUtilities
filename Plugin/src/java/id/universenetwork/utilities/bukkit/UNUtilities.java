package id.universenetwork.utilities.bukkit;

import id.universenetwork.utilities.bukkit.events.ReloadConfigEvent;
import id.universenetwork.utilities.bukkit.libraries.InfinityLib.Core.YamlBuilder;
import id.universenetwork.utilities.bukkit.manager.API;
import id.universenetwork.utilities.bukkit.manager.Commands;
import id.universenetwork.utilities.bukkit.manager.Features;
import id.universenetwork.utilities.bukkit.manager.UpdateChecker;
import id.universenetwork.utilities.bukkit.utils.Logger;
import id.universenetwork.utilities.bukkit.utils.Text;
import id.universenetwork.utilities.Universal.Utils.TookTimer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * UNUtilities - Bukkit
 */
public final class UNUtilities extends JavaPlugin {
    public static UNUtilities plugin;
    public static YamlBuilder cfg;
    public static YamlBuilder data;
    public static String prefix;
    public static final String commit = "0686cb8";
    private int u;

    /**
     * Initialize config, data, and checking for updates before plugin is enabled
     */
    @Override
    public void onLoad() {
        TookTimer t = new TookTimer();
        plugin = this;
        getLogger().info("┬žeInitializing Config & Data Manager...");
        try {
            cfg = new YamlBuilder("config.yml");
            data = new YamlBuilder("data.yml");
            prefix = Text.translateColor(cfg.getString("Settings.prefix"));
            Logger.info("&aConfig & Data Manager has been initialized! &b");
        } catch (RuntimeException e) {
            Logger.log(Level.SEVERE, "Failed to initialize Config & Data Manager!", e);
        }
        Logger.info("&bTook " + t.get() + "ms to &aload&b!");
    }

    @Override
    public void onEnable() {
        TookTimer t = new TookTimer();
        API.init();
        Commands.init();
        Commands.register(new MainCommand());
        Features.init();
        Bukkit.getLogger().info("\n┬žb __    __  ┬že.__   __.  ┬ž9__    __  .___________. __   __       __  .___________. __   _______     _______.\n" +
                "┬žb|  |  |  | ┬že|  \\ |  | ┬ž9|  |  |  | |           ||  | |  |     |  | |           ||  | |   ____|   /       |\n" +
                "┬žb|  |  |  | ┬že|   \\|  | ┬ž9|  |  |  | `---|  |----`|  | |  |     |  | `---|  |----`|  | |  |__     |   (----`\n" +
                "┬žb|  |  |  | ┬že|  . `  | ┬ž9|  |  |  |     |  |     |  | |  |     |  |     |  |     |  | |   __|     \\   \\    \n" +
                "┬žb|  `--'  | ┬že|  |\\   | ┬ž9|  `--'  |     |  |     |  | |  `----.|  |     |  |     |  | |  |____.----)   |   \n" +
                "┬žb \\______/  ┬že|__| \\__|  ┬ž9\\______/      |__|     |__| |_______||__|     |__|     |__| |_______|_______/    \n" +
                "┬ža             _  _ ____ ____    ___  ____ ____ _  _    ____ _  _ ____ ___  _    ____ ___\n" +
                "┬ža             |__| |__| [__     |__] |___ |___ |\\ |    |___ |\\ | |__| |__] |    |___ |  \\ \n" +
                "┬ža             |  | |  | ___]    |__] |___ |___ | \\|    |___ | \\| |  | |__] |___ |___ |__/\n");
        Logger.info("&bTook " + t.get() + "ms to &aenable&b!");
        UpdateChecker.init();
    }

    @Override
    public void onDisable() {
        TookTimer t = new TookTimer();
        API.declare();
        Bukkit.getLogger().info("\n┬žb __    __  ┬že.__   __.  ┬ž9__    __  .___________. __   __       __  .___________. __   _______     _______.\n" +
                "┬žb|  |  |  | ┬že|  \\ |  | ┬ž9|  |  |  | |           ||  | |  |     |  | |           ||  | |   ____|   /       |\n" +
                "┬žb|  |  |  | ┬že|   \\|  | ┬ž9|  |  |  | `---|  |----`|  | |  |     |  | `---|  |----`|  | |  |__     |   (----`\n" +
                "┬žb|  |  |  | ┬že|  . `  | ┬ž9|  |  |  |     |  |     |  | |  |     |  |     |  |     |  | |   __|     \\   \\    \n" +
                "┬žb|  `--'  | ┬že|  |\\   | ┬ž9|  `--'  |     |  |     |  | |  `----.|  |     |  |     |  | |  |____.----)   |   \n" +
                "┬žb \\______/  ┬že|__| \\__|  ┬ž9\\______/      |__|     |__| |_______||__|     |__|     |__| |_______|_______/    \n" +
                "┬žc           _  _ ____ ____    ___  ____ ____ _  _    ___  _ ____ ____ ___  _    ____ ___\n" +
                "┬žc           |__| |__| [__     |__] |___ |___ |\\ |    |  \\ | [__  |__| |__] |    |___ |  \\\n" +
                "┬žc           |  | |  | ___]    |__] |___ |___ | \\|    |__/ | ___] |  | |__] |___ |___ |__/\n");
        Logger.info("&bTook " + t.get() + "ms to &cdisable&b!");
    }

    /**
     * Create a NamespacedKey without calling plugin instance
     */
    public static NamespacedKey createKey(String Key) {
        return new NamespacedKey(plugin, Key);
    }

    /**
     * Reload config & data and calling Reload Event
     */
    public static void reloadCfg() {
        TookTimer t = new TookTimer();
        Logger.info("&eReloading Configuration & Data...");
        cfg.reload();
        data.reload();
        Bukkit.getPluginManager().callEvent(new ReloadConfigEvent());
        prefix = Text.translateColor(cfg.getString("Settings.prefix"));
        Logger.info("&aConfiguration & Data has been reloaded! &bTook " + t.get() + "ms");
    }

    public static List<String> getOnlinePlayers(String partialName) {
        return filterStartingWith(partialName, Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName));
    }

    public static List<String> filterStartingWith(String prefix, Stream<String> stream) {
        return stream.filter(s -> s != null && !s.isEmpty() && s.toLowerCase().startsWith(prefix.toLowerCase())).collect(Collectors.toList());
    }
}