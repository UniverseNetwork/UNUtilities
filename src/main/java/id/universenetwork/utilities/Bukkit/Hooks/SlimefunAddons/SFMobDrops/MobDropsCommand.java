package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Enums.Settings.DENYCONSOLE;
import static id.universenetwork.utilities.Bukkit.Enums.Settings.NOPERMISSION;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops.SFMobDrops.enabled;
import static id.universenetwork.utilities.Bukkit.Manager.Config.Settings;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;

public class MobDropsCommand implements TabExecutor {
    final List<String> arg0 = Collections.singletonList("list");

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("sfmobdrops.admin")) {
            sender.sendMessage(Settings(NOPERMISSION));
            return true;
        }
        if (enabled) {
            if (args.length == 0) sendUsage(sender);
            else {
                if (args[0].equalsIgnoreCase("list")) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(Settings(DENYCONSOLE));
                        return true;
                    }
                    Guis.openMobDropList((Player) sender);
                } else sendUsage(sender);
            }
        } else sender.sendMessage(get().getString(ADDONSSETTINGS.getConfigPath() + "SFMobDrops.disabledMessage"));
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], arg0, new ArrayList<>());
        } else return Collections.emptyList();
    }

    void sendUsage(@NotNull CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "----------" + ChatColor.GOLD + "SFMobDrops" + ChatColor.GRAY + "----------" + '\n' + ChatColor.GOLD + "/mobdrops list" + ChatColor.GRAY + " - Get a list of the mob drops");
    }
}