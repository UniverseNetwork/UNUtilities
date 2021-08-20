package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.ChangeSlots;
import id.universenetwork.utilities.Bukkit.Commands.Hat;
import id.universenetwork.utilities.Bukkit.Commands.UNU;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public abstract class Commands implements CommandExecutor, TabCompleter {
    private final String Name;
    private final String Permission;
    private final boolean PlayerOnly;

    public Commands(final String Name, String Permission, boolean PlayerOnly) {
        this.Name = Name;
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
        plugin.getCommand(Name).setExecutor(this);
    }

    public abstract void Execute(CommandSender sender, Command command, String[] args);

    public abstract List<String> TabComplete(CommandSender sender, Command command, String str, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (!sender.hasPermission(Permission)) {
            sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return false;
        }
        if (PlayerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return false;
        }
        Execute(sender, cmd, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(Permission)) sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
        if (PlayerOnly && !(sender instanceof Player))
            sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
        TabComplete(sender, command, alias, args);
        return null;
    }

    public static void register() {
        new UNU();
        new Hat();
        new ChangeSlots();
    }
}