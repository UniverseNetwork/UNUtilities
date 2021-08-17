package id.universenetwork.utilities.Bukkit;

import id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Enums.Features.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import id.universenetwork.utilities.Bukkit.Manager.Event;
import id.universenetwork.utilities.Bukkit.Manager.TabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.primesoft.asyncworldedit.api.IAsyncWorldEdit;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerEntry;
import org.primesoft.asyncworldedit.api.progressDisplay.IProgressDisplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;

public final class UNUtilities extends JavaPlugin implements IProgressDisplay {
    private final HashMap<UUID, BossBar> bossBars = new HashMap<>();
    public static UNUtilities plugin;
    public static String prefix;
    public static Boolean aweHook = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Config.setup();
        Event.register();
        Commands.register();
        TabCompleter.register();
        if (Config.AWEBDSettings(AsyncWorldEditBossBarDisplay.ENABLED)) {
            System.out.println(prefix + " §6AsyncWorldEdit BossBar Display Features is enabled on config.yml. Searching AsyncWorldEdit...");
            if (Bukkit.getPluginManager().getPlugin("AsyncWorldEdit").isEnabled()) {
                System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
                IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
                awe.getProgressDisplayManager().registerProgressDisplay(this);
                aweHook = true;
                System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
            } else {
                System.out.println(prefix + " §cAsyncWorldEdit not found. You need AsyncWorldEdit to use AsyncWorldEdit BossBar Display Features");
                Config.setBoolean("Features.AsyncWorldEditBossBarDisplay.enabled", false);
            }
        }
        System.out.println(prefix + " §bU§eN§9Utilities §dhas been enabled");
    }

    @Override
    // Plugin shutdown logic
    public void onDisable() {
        System.out.println(prefix + " §bU§eN§9Utilities §chas been disabled");
        if (Config.MPCCSettings(MaxPlayerChangerCommand.SOR) && Config.MPCCSettings(MaxPlayerChangerCommand.ENABLED))
            updateServerProperties();
        if (aweHook) {
            IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
            awe.getProgressDisplayManager().unregisterProgressDisplay(this);
            System.out.println(prefix + " §cSuccessfully unhooked with AsyncWorldEdit");
        }
    }

    private void updateServerProperties() {
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
            getLogger().log(Level.SEVERE, "Error while saving max players in server properties", v11);
        }
    }

    @Override
    public void disableMessage(IPlayerEntry player) {
        if (bossBars.containsKey(player.getUUID())) {
            BossBar bossBar = bossBars.get(player.getUUID());
            bossBar.setVisible(false);
        }
    }

    @Override
    public void setMessage(IPlayerEntry player, int jobsCount, int queuedBlocks, int maxQueuedBlocks, double timeLeft, double placingSpeed, double percentage) {
        Player p = Bukkit.getPlayer(player.getUUID());
        BossBar bossBar;
        if (this.bossBars.containsKey(player.getUUID())) {
            bossBar = bossBars.get(player.getUUID());
        } else {
            BarColor color = BarColor.valueOf(Config.AWEBDMessage(AsyncWorldEditBossBarDisplay.COLOR));
            bossBar = Bukkit.createBossBar("", color, BarStyle.SOLID, new BarFlag[0]);
            bossBar.addPlayer(p);
        }

        if (!bossBar.getPlayers().contains(Bukkit.getPlayer(player.getUUID()))) {
            bossBar.addPlayer(p);
        }

        double progress = Math.max(0.0D, Math.min(1.0D, percentage / 100.0D));
        bossBar.setProgress(progress);
        NumberFormat nf = new DecimalFormat("#.##");
        String format = Config.AWEBDMessage(AsyncWorldEditBossBarDisplay.TITLE);
        format = ChatColor.translateAlternateColorCodes('&', format == null ? "ETA: $timeLeft seconds, Speed: $placingSpeed block/sec, $percentage %" : format);
        format = format.replace("$jobsCount", jobsCount + "").replace("$queuedBlocks", queuedBlocks + "").replace("$maxQueuedBlocks", maxQueuedBlocks + "").replace("$timeLeft", nf.format(timeLeft)).replace("$placingSpeed", nf.format(placingSpeed)).replace("$percentage", nf.format(percentage));
        bossBar.setTitle(format);
        bossBar.setVisible(true);
        bossBars.put(player.getUUID(), bossBar);
    }

}