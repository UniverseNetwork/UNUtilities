package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Events.UNUtilitiesDisableEvent;
import id.universenetwork.utilities.Bukkit.Manager.*;
import id.universenetwork.utilities.Bukkit.Tasks.CompatibilityCheckTask;
import id.universenetwork.utilities.Bukkit.Tasks.MainTask;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Enums.Features.VillagerOptimization.TPAS;
import static id.universenetwork.utilities.Bukkit.Enums.Features.VillagerOptimization.VCPP;
import static id.universenetwork.utilities.Bukkit.Manager.API.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.VOEnabled;
import static id.universenetwork.utilities.Bukkit.Manager.Config.VOLong;
import static java.util.logging.Level.SEVERE;
import static java.util.stream.Collectors.toList;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getScheduler;

public final class UNUtilities extends JavaPlugin {
    // I could replace this with a LongSet but for some reason craftbukkit wont import
    // it's micro optimizations anyways :P
    public static final Set<Point> VANILLA_CHUNKS = new HashSet<>();
    public static long maxChunks;
    public static UNUtilities plugin;
    public static String prefix;
    public static Boolean aweHook = false;
    BukkitTask task;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Config.setup();
        ActionBarAPISetup();
        NoteBlockAPISetup("enabling");
        HamsterAPISetup("enabling");
        Listeners.register();
        Commands.register();
        Hooks.AsyncWorldEditBossBarDisplay("enabling");
        Hooks.ShopGUIPlusSilkSpawnersConnector();
        Hooks.SlimeFunAddons();
        Hooks.SkriptAddons();
        if (VOEnabled() && new CompatibilityCheckTask().passedCheck()) {
            maxChunks = VOLong(VCPP);
            if (task != null) task.cancel();
            task = getScheduler().runTaskTimer(this, new MainTask(), 0L, VOLong(TPAS) <= 0 ? 600 : VOLong(TPAS));
            loadAAVLP();
        }
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§d         █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▀ █▄░█ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§d         █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   ██▄ █░▀█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getPluginManager().callEvent(new UNUtilitiesDisableEvent());
        NoteBlockAPISetup("disabling");
        HamsterAPISetup("disabling");
        if (Config.MPCCBoolean(MaxPlayerChangerCommand.SOR) && Config.MPCCBoolean(MaxPlayerChangerCommand.ENABLED))
            updateServerProperties();
        saveAAVLP();
        Hooks.AsyncWorldEditBossBarDisplay("disabling");
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§c        █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▄ █ █▀ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§c        █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   █▄▀ █ ▄█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    void updateServerProperties() {
        Properties properties = new Properties();
        File propertiesFile = new File("server.properties");
        try {
            FileInputStream is = new FileInputStream(propertiesFile);
            try {
                properties.load(is);
            } catch (Throwable v10) {
                try {
                    is.close();
                } catch (Throwable v8) {
                    v10.addSuppressed(v8);
                }
                throw v10;
            }
            is.close();
            String maxPlayers = Integer.toString(getServer().getMaxPlayers());
            if (properties.getProperty("max-players").equals(maxPlayers)) return;
            System.out.println(prefix + " §6Saving max players to server.properties...");
            properties.setProperty("max-players", maxPlayers);
            FileOutputStream os = new FileOutputStream(propertiesFile);
            try {
                properties.store(os, "Minecraft server properties");
            } catch (Throwable v9) {
                try {
                    os.close();
                } catch (Throwable v7) {
                    v9.addSuppressed(v7);
                }
                throw v9;
            }
            os.close();
        } catch (IOException v11) {
            getLogger().log(SEVERE, prefix + " §cError while saving max players in server properties", v11);
        }
    }

    public static boolean isInVanilla(Entity villager) {
        Location loc = villager.getLocation();
        return VANILLA_CHUNKS.contains(new Point(loc.getBlockX() >> 4, loc.getBlockZ() >> 4));
    }

    public void loadAAVLP() {
        @NotNull List<Long> chunk = Data.get().getLongList("chunks");
        if (chunk == null) saveAAVLP();
        chunk.stream().mapToLong(Long::longValue).mapToObj(UNUtilities::to).forEach(VANILLA_CHUNKS::add);
    }

    public void saveAAVLP() {
        Data.set("chunks", VANILLA_CHUNKS.stream().mapToLong(UNUtilities::from).boxed().collect(toList()));
    }

    public static long from(Point p) {
        return (long) p.x << 32 | p.y & 0xFFFFFFFFL;
    }

    public static Point to(long l) {
        return new Point((int) (l >> 32), (int) l);
    }
}