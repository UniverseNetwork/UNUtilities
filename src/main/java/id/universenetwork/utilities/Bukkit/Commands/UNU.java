package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Settings;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Color.sendTranslate;

public class UNU extends Commands {
    public UNU() {
        super("UniverseUtilities", "Main command for UNUtilities", "unutilities.command.reload", false, "unu", "uu");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
                Config.reload();
                sender.sendMessage(Config.Settings(Settings.RELOAD));
            } else sendHelp(sender);
        } else sendHelp(sender);
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        if (args.length == 1) {
            List<String> arg = new ArrayList<>();
            arg.add("reload");
            arg.add("rl");
            return arg;
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getAliases() {
        List<String> alias = new ArrayList<>();
        alias.add("unu");
        alias.add("uu");
        return alias;
    }

    void sendHelp(CommandSender sender) {
        sendTranslate(sender, "&b&lU&e&lN&9&lUtilities");
        sendTranslate(sender, "");
        sendTranslate(sender, "&d/" + getName() + " reload");
    }
}
