package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobDropsCommand extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    final List<String> arg0 = Collections.singletonList("list");

    public MobDropsCommand() {
        super("mobdrops", "SFMobDrops Slimefun Addons Command", "sfmobdrops.admin", true, "sfmobdrops", "mobdrop", "sfmd");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (args.length == 0) sendUsage(sender);
        else {
            if (args[0].equalsIgnoreCase("list")) Guis.openMobDropList((Player) sender);
            else sendUsage(sender);
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        if (args.length == 1) return StringUtil.copyPartialMatches(args[0], arg0, new ArrayList<>());
        else return Collections.emptyList();
    }

    void sendUsage(@NotNull CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "----------" + ChatColor.GOLD + "SFMobDrops" + ChatColor.GRAY + "----------" + '\n' + ChatColor.GOLD + "/mobdrops list" + ChatColor.GRAY + " - Get a list of the mob drops");
    }
}