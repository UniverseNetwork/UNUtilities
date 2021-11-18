package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.command.CommandSender;

import java.util.List;

import static id.universenetwork.utilities.Bukkit.Utils.Color.sendTranslate;

public class UniverseUtilities extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    public UniverseUtilities() {
        super("UniverseUtilities", "Main command for UNUtilities", "unutilities.command.reload", false, "unu", "uu");
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (args.length == 1 && (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl"))) {
            Config.reload();
            sender.sendMessage(Config.Settings(id.universenetwork.utilities.Universal.Enums.Settings.RELOAD));
        } else {
            sendTranslate(sender, "&b&lU&e&lN&9&lUtilities");
            sendTranslate(sender, "");
            sendTranslate(sender, "&d/" + getName() + " reload");
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        if (args.length == 1) {
            List<String> arg = new java.util.ArrayList<>();
            arg.add("reload");
            arg.add("rl");
            return arg;
        }
        return java.util.Collections.emptyList();
    }
}