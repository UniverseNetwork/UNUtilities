package id.universenetwork.utilities.Bukkit.Hooks;

import id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.primesoft.asyncworldedit.api.IAsyncWorldEdit;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerEntry;
import org.primesoft.asyncworldedit.api.progressDisplay.IProgressDisplay;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class AsyncWorldEdit implements IProgressDisplay {
    private final HashMap<UUID, BossBar> bossBars = new HashMap<>();

    public static void hooks() {
        System.out.println(prefix + " §6Found AsyncWorldEdit. Hooking...");
        IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
        awe.getProgressDisplayManager().registerProgressDisplay(new AsyncWorldEdit());
        System.out.println(prefix + " §aSuccessfully hooked with AsyncWorldEdit");
    }

    public static void unhooks() {
        System.out.println(prefix + " §6Unhooking with AsyncWorldEdit...");
        IAsyncWorldEdit awe = (IAsyncWorldEdit) Bukkit.getPluginManager().getPlugin("AsyncWorldEdit");
        awe.getProgressDisplayManager().unregisterProgressDisplay(new AsyncWorldEdit());
        System.out.println(prefix + " §cSuccessfully unhooked with AsyncWorldEdit");
    }

    @Override
    public String getName() {
        return plugin.getName();
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
