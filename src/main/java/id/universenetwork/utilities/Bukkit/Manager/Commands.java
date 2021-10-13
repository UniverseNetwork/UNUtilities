package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.ChangeSlots;
import id.universenetwork.utilities.Bukkit.Commands.Hat;
import id.universenetwork.utilities.Bukkit.Commands.UNU;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public abstract class Commands implements TabExecutor {
    final String Name;
    final String Permission;
    final boolean PlayerOnly;

    public Commands(String Name, String Permission, boolean PlayerOnly) {
        this.Name = Name;
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
        plugin.getCommand(Name).setExecutor(this);
        plugin.getCommand(Name).setTabCompleter(this);
    }

    public abstract void Execute(CommandSender sender, Command command, String[] args);

    public abstract List<String> TabComplete(CommandSender sender, Command command, String str, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (!sender.hasPermission(Permission)) {
            sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return true;
        }
        if (PlayerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return true;
        }
        Execute(sender, cmd, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(Permission)) sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
        if (PlayerOnly && !(sender instanceof Player)) sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
        return TabComplete(sender, command, alias, args);
    }

    public static void register() {
        System.out.println(prefix + " §eRegistering Commands...");
        new UNU();
        new Hat();
        new ChangeSlots();
        // new Keep();

        System.out.println(prefix + " §aAll Commands Successfully Registered");
    }
}