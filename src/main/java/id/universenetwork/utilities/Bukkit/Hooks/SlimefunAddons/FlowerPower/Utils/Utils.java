package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils;

import id.universenetwork.utilities.Bukkit.Utils.Color;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;

/**
 * Utility class
 *
 * @author NCBPFluffyBear
 */
public class Utils {
    public static void send(org.bukkit.command.CommandSender s, String msg) {
        s.sendMessage(Color.Translator("&5&l[Magic] " + msg));
    }

    public static int getExpAtLevel(int level) {
        if (level <= 15) return (2 * level) + 7;
        if (level <= 30) return (5 * level) - 38;
        return (9 * level) - 158;
    }

    public static int getTotalExperience(final org.bukkit.entity.Player p) {
        int currentLevel = p.getLevel();
        int exp = Math.round(getExpAtLevel(currentLevel) * p.getExp());
        while (currentLevel > 0) {
            currentLevel--;
            exp += getExpAtLevel(currentLevel);
        }
        if (exp < 0) exp = 0;
        return exp;
    }

    public static org.bukkit.scheduler.BukkitTask runSync(Runnable r, long delay) {
        if (Slimefun.instance() == null || !Slimefun.instance().isEnabled()) return null;
        return org.bukkit.Bukkit.getScheduler().runTaskLater(Slimefun.instance(), r, delay);
    }
}