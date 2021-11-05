package id.universenetwork.utilities.Bukkit.Hooks;

import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.primesoft.asyncworldedit.api.IAsyncWorldEdit;
import org.primesoft.asyncworldedit.api.playerManager.IPlayerEntry;

import java.util.HashMap;

import static id.universenetwork.utilities.Bukkit.Manager.Config.AWEBDMessage;
import static org.bukkit.Bukkit.*;

public class AsyncWorldEditBossBarDisplay implements org.primesoft.asyncworldedit.api.progressDisplay.IProgressDisplay {
    final HashMap<java.util.UUID, BossBar> bossBars = new HashMap<>();
    static final IAsyncWorldEdit awe = (IAsyncWorldEdit) getPluginManager().getPlugin("AsyncWorldEdit");

    public static void hooks() {
        System.out.println(UNUtilities.prefix + " §6Found AsyncWorldEdit. Hooking...");
        awe.getProgressDisplayManager().registerProgressDisplay(new AsyncWorldEditBossBarDisplay());
        System.out.println(UNUtilities.prefix + " §aSuccessfully hooked with AsyncWorldEdit");
    }

    public static void unhooks() {
        System.out.println(UNUtilities.prefix + " §6Unhooking with AsyncWorldEdit...");
        awe.getProgressDisplayManager().unregisterProgressDisplay(new AsyncWorldEditBossBarDisplay());
        System.out.println(UNUtilities.prefix + " §cSuccessfully unhooked with AsyncWorldEdit");
    }

    @Override
    public String getName() {
        return UNUtilities.plugin.getName();
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
        org.bukkit.entity.Player p = getPlayer(player.getUUID());
        BossBar bossBar;
        if (this.bossBars.containsKey(player.getUUID())) bossBar = bossBars.get(player.getUUID());
        else {
            BarColor color = BarColor.valueOf(AWEBDMessage(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.COLOR));
            bossBar = createBossBar("", color, org.bukkit.boss.BarStyle.SOLID);
            bossBar.addPlayer(p);
        }
        if (!bossBar.getPlayers().contains(getPlayer(player.getUUID()))) bossBar.addPlayer(p);
        double progress = Math.max(0.0D, Math.min(1.0D, percentage / 100.0D));
        bossBar.setProgress(progress);
        java.text.NumberFormat nf = new java.text.DecimalFormat("#.##");
        String format = AWEBDMessage(id.universenetwork.utilities.Bukkit.Enums.Features.AsyncWorldEditBossBarDisplay.TITLE);
        format = id.universenetwork.utilities.Bukkit.Manager.Color.Translator(format == null ? "ETA: $timeLeft seconds, Speed: $placingSpeed block/sec, $percentage %" : format);
        format = format.replace("$jobsCount", jobsCount + "").replace("$queuedBlocks", queuedBlocks + "").replace("$maxQueuedBlocks", maxQueuedBlocks + "").replace("$timeLeft", nf.format(timeLeft)).replace("$placingSpeed", nf.format(placingSpeed)).replace("$percentage", nf.format(percentage));
        bossBar.setTitle(format);
        bossBar.setVisible(true);
        bossBars.put(player.getUUID(), bossBar);
    }
}