package id.universenetwork.utilities.Manager;

import id.universenetwork.utilities.Enums.Settings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("unutilities.command")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                    Config.reload();
                    Config.setup();
                    Proxy.setup();
                    Sender.send(sender, Config.Settings(Settings.RELOAD).replaceAll("%p%", Config.Settings(Settings.PREFIX)));
                } else sendHelp(sender, command);
            } else sendHelp(sender, command);
        } else {
            Sender.send(sender, Config.Settings(Settings.NOPERMISSION));
            return false;
        }
        return true;
    }

    private void sendHelp(CommandSender sender, Command cmd) {
        Sender.send(sender, "&b&lU&e&lN&9&lUtilities");
        Sender.send(sender, "");
        Sender.send(sender, "&d/" + cmd.getName() + " reload");
    }
}
