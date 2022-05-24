package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Events.ReloadConfigEvent;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Core.YamlBuilder;
import id.universenetwork.utilities.Bukkit.Manager.API;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Features;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import id.universenetwork.utilities.Bukkit.Utils.TookTimer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
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

    /**
     * Initialize config & data before plugin is enabled
     */
    @Override
    public void onLoad() {
        plugin = this;
        getLogger().info("§eInitializing Config & Data Manager...");
        TookTimer t = new TookTimer();
        try {
            cfg = new YamlBuilder("config.yml");
            data = new YamlBuilder("data.yml");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        prefix = Text.translateColor(cfg.getString("Settings.prefix"));
        Logger.info("&aConfig & Data Manager has been initialized! &bTook " + t.get() + "ms");
    }

    @Override
    public void onEnable() {
        TookTimer t = new TookTimer();
        API.init();
        Commands.init();
        Commands.register(new MainCommand());
        Features.init();
        System.out.println("§b __    __  §e.__   __.  §9__    __  .___________. __   __       __  .___________. __   _______     _______.\n" +
                "§b|  |  |  | §e|  \\ |  | §9|  |  |  | |           ||  | |  |     |  | |           ||  | |   ____|   /       |\n" +
                "§b|  |  |  | §e|   \\|  | §9|  |  |  | `---|  |----`|  | |  |     |  | `---|  |----`|  | |  |__     |   (----`\n" +
                "§b|  |  |  | §e|  . `  | §9|  |  |  |     |  |     |  | |  |     |  |     |  |     |  | |   __|     \\   \\    \n" +
                "§b|  `--'  | §e|  |\\   | §9|  `--'  |     |  |     |  | |  `----.|  |     |  |     |  | |  |____.----)   |   \n" +
                "§b \\______/  §e|__| \\__|  §9\\______/      |__|     |__| |_______||__|     |__|     |__| |_______|_______/    \n" +
                "§a             _  _ ____ ____    ___  ____ ____ _  _    ____ _  _ ____ ___  _    ____ ___\n" +
                "§a             |__| |__| [__     |__] |___ |___ |\\ |    |___ |\\ | |__| |__] |    |___ |  \\ \n" +
                "§a             |  | |  | ___]    |__] |___ |___ | \\|    |___ | \\| |  | |__] |___ |___ |__/\n");
        Logger.info("&bTook " + t.get() + "ms to &aenable&b!");
    }

    @Override
    public void onDisable() {
        TookTimer t = new TookTimer();
        API.declare();
        System.out.println("§b __    __  §e.__   __.  §9__    __  .___________. __   __       __  .___________. __   _______     _______.\n" +
                "§b|  |  |  | §e|  \\ |  | §9|  |  |  | |           ||  | |  |     |  | |           ||  | |   ____|   /       |\n" +
                "§b|  |  |  | §e|   \\|  | §9|  |  |  | `---|  |----`|  | |  |     |  | `---|  |----`|  | |  |__     |   (----`\n" +
                "§b|  |  |  | §e|  . `  | §9|  |  |  |     |  |     |  | |  |     |  |     |  |     |  | |   __|     \\   \\    \n" +
                "§b|  `--'  | §e|  |\\   | §9|  `--'  |     |  |     |  | |  `----.|  |     |  |     |  | |  |____.----)   |   \n" +
                "§b \\______/  §e|__| \\__|  §9\\______/      |__|     |__| |_______||__|     |__|     |__| |_______|_______/    \n" +
                "§c           _  _ ____ ____    ___  ____ ____ _  _    ___  _ ____ ____ ___  _    ____ ___\n" +
                "§c           |__| |__| [__     |__] |___ |___ |\\ |    |  \\ | [__  |__| |__] |    |___ |  \\\n" +
                "§c           |  | |  | ___]    |__] |___ |___ | \\|    |__/ | ___] |  | |__] |___ |___ |__/\n");
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