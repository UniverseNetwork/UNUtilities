package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.UNUtilities;
import net.md_5.bungee.api.CommandSender;

import static id.universenetwork.utilities.Bungee.Utils.Color.sendTranslate;

public class UniverseUtilitiesBungee extends id.universenetwork.utilities.Bungee.Manager.Commands {
    public UniverseUtilitiesBungee() {
        super("UniverseUtilitiesBungee", "unutilities.command.reload", false, "unub", "uub");
    }

    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Args.length == 1 && (Args[0].equalsIgnoreCase("reload") || Args[0].equalsIgnoreCase("rl"))) {
            UNUtilities.settings = new id.universenetwork.utilities.Bungee.Manager.Settings(true);
            UNUtilities.prefix = id.universenetwork.utilities.Bungee.Manager.Config.Settings(id.universenetwork.utilities.Bungee.Enums.Settings.PREFIX);
            Sender.sendMessage(id.universenetwork.utilities.Bungee.Manager.Config.Settings(id.universenetwork.utilities.Bungee.Enums.Settings.RELOAD));
        } else {
            sendTranslate(Sender, "&b&lU&e&lN&9&lUtilities");
            sendTranslate(Sender, "");
            sendTranslate(Sender, "&d/" + getName() + " reload");
        }
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        if (Args.length == 1) {
            java.util.List<String> arg = new java.util.ArrayList<>();
            arg.add("reload");
            arg.add("rl");
            return arg;
        }
        return java.util.Collections.emptyList();
    }
}