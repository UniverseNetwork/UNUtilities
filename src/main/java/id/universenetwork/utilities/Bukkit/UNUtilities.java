package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Enums.Features.VillagerOptimization;
import id.universenetwork.utilities.Bukkit.Manager.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static id.universenetwork.utilities.Bukkit.Manager.API.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;
import static id.universenetwork.utilities.Bukkit.Manager.Hooks.*;

public final class UNUtilities extends org.bukkit.plugin.java.JavaPlugin {
    // I could replace this with a LongSet but for some reason craftbukkit won't import
    // it's micro optimizations anyways :P
    public static final java.util.Set<Point> VANILLA_CHUNKS = new java.util.HashSet<>();
    public static long maxChunks;
    public static UNUtilities plugin;
    public static String prefix;
    public static Boolean aweHook = false;
    org.bukkit.scheduler.BukkitTask task;

    @Override
    public void onLoad() {
        // Plugin loaded logic
        plugin = this;
        setup();
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§d                     █░░ █▀█ ▄▀█ █▀▄ █ █▄░█ █▀▀ ░ ░ ░\n" +
                "§d                     █▄▄ █▄█ █▀█ █▄▀ █ █░▀█ █▄█ ▄ ▄ ▄\n\n\n");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Data.setup();
        Proxy.setup();
        new id.universenetwork.utilities.Bukkit.Handlers.BookExploitHandler();
        id.universenetwork.utilities.Bukkit.NMS.ETF.setup();
        ActionBarAPISetup();
        NoteBlockAPISetup("enabling");
        HamsterAPISetup("enabling");
        Listeners.register();
        Commands.Register();
        AsyncWorldEditBossBarDisplay("enabling");
        ShopGUIPlusSilkSpawnersConnector();
        SlimefunAddons();
        SkriptAddons();
        ViaLegacy();
        if (VOEnabled() && new id.universenetwork.utilities.Bukkit.Tasks.CompatibilityCheckTask().passedCheck()) {
            maxChunks = VOLong(VillagerOptimization.VCPP);
            if (task != null) task.cancel();
            task = getServer().getScheduler().runTaskTimer(this, new id.universenetwork.utilities.Bukkit.Tasks.MainTask(), 0L, VOLong(VillagerOptimization.TPAS) <= 0 ? 600 : VOLong(VillagerOptimization.TPAS));
            loadAAVLP();
        }
        System.out.println("\n\n\n" +
                "§b██╗░░░██╗§e███╗░░██╗§9██╗░░░██╗████████╗██╗██╗░░░░░██╗████████╗██╗███████╗░██████╗\n" +
                "§b██║░░░██║§e████╗░██║§9██║░░░██║╚══██╔══╝██║██║░░░░░██║╚══██╔══╝██║██╔════╝██╔════╝\n" +
                "§b██║░░░██║§e██╔██╗██║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║█████╗░░╚█████╗░\n" +
                "§b██║░░░██║§e██║╚████║§9██║░░░██║░░░██║░░░██║██║░░░░░██║░░░██║░░░██║██╔══╝░░░╚═══██╗\n" +
                "§b╚██████╔╝§e██║░╚███║§9╚██████╔╝░░░██║░░░██║███████╗██║░░░██║░░░██║███████╗██████╔╝\n" +
                "§b░╚═════╝░§e╚═╝░░╚══╝§9░╚═════╝░░░░╚═╝░░░╚═╝╚══════╝╚═╝░░░╚═╝░░░╚═╝╚══════╝╚═════╝░\n\n" +
                "§a         █░█ ▄▀█ █▀   █▄▄ █▀▀ █▀▀ █▄░█   █▀▀ █▄░█ ▄▀█ █▄▄ █░░ █▀▀ █▀▄\n" +
                "§a         █▀█ █▀█ ▄█   █▄█ ██▄ ██▄ █░▀█   ██▄ █░▀█ █▀█ █▄█ █▄▄ ██▄ █▄▀\n\n\n");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getPluginManager().callEvent(new id.universenetwork.utilities.Bukkit.Events.UNUtilitiesDisableEvent());
        NoteBlockAPISetup("disabling");
        HamsterAPISetup("disabling");
        if (Config.MPCCBoolean(MaxPlayerChangerCommand.SOR) && Config.MPCCBoolean(MaxPlayerChangerCommand.ENABLED))
            updateServerProperties();
        saveAAVLP();
        AsyncWorldEditBossBarDisplay("disabling");
        plugin = null;
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
            try (java.io.InputStream is = new FileInputStream(propertiesFile)) {
                properties.load(is);
            }
            String maxPlayers = Integer.toString(getServer().getMaxPlayers());
            if (properties.getProperty("max-players").equals(maxPlayers)) return;
            System.out.println(prefix + " §eSaving max players to server.properties...");
            properties.setProperty("max-players", maxPlayers);
            try (java.io.OutputStream os = new FileOutputStream(propertiesFile)) {
                properties.store(os, "Minecraft server properties");
            }
        } catch (java.io.IOException e) {
            org.bukkit.Bukkit.getLogger().log(java.util.logging.Level.SEVERE, prefix + " §cAn error occurred while updating the server properties", e);
        }
    }

    public static boolean isInVanilla(org.bukkit.entity.Entity villager) {
        org.bukkit.Location loc = villager.getLocation();
        return VANILLA_CHUNKS.contains(new Point(loc.getBlockX() >> 4, loc.getBlockZ() >> 4));
    }

    public void loadAAVLP() {
        @org.jetbrains.annotations.NotNull java.util.List<Long> chunk = Data.get().getLongList("chunks");
        if (chunk == null) saveAAVLP();
        chunk.stream().mapToLong(Long::longValue).mapToObj(UNUtilities::to).forEach(VANILLA_CHUNKS::add);
    }

    public void saveAAVLP() {
        Data.set("chunks", VANILLA_CHUNKS.stream().mapToLong(UNUtilities::from).boxed().collect(java.util.stream.Collectors.toList()));
    }

    public static long from(Point p) {
        return (long) p.x << 32 | p.y & 0xFFFFFFFFL;
    }

    public static Point to(long l) {
        return new Point((int) (l >> 32), (int) l);
    }
}