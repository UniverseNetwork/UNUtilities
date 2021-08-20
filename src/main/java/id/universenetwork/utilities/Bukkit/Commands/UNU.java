package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Color.sendTranslate;

public class UNU extends Commands {
    public UNU() {
        super("universeutilities", "unutilities.command.reload", false);
    }

    @Override
    public void Execute(CommandSender sender, Command command, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                Config.reload();
                sender.sendMessage(Config.Settings(Settings.RELOAD));
            } else sendHelp(sender, command);
        } else sendHelp(sender, command);
    }

    @Override
    public List<String> TabComplete(CommandSender sender, Command command, String str, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");
            arguments.add("rl");
            return arguments;
        }
        return Collections.emptyList();
    }

    private void sendHelp(CommandSender sender, Command cmd) {
        sendTranslate(sender, "&b&lU&e&lN&9&lUtilities");
        sendTranslate(sender, "");
        sendTranslate(sender, "&d/" + cmd.getName() + " reload");
    }
}
