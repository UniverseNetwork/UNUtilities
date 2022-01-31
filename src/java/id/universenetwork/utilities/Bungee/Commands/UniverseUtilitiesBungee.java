package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.UNUtilities;
import id.universenetwork.utilities.Bungee.Utils.YamlBuilder;
import id.universenetwork.utilities.Universal.Enums.Settings;
import net.md_5.bungee.api.CommandSender;

import static id.universenetwork.utilities.Bungee.Utils.Color.sendTranslate;

@id.universenetwork.utilities.Universal.Annotations.CommandProperties(Name = "UniverseUtilitiesBungee", Permission = "unutilities.command.reload", PlayerOnly = false, Aliases = {"unub", "uub"})
public class UniverseUtilitiesBungee extends id.universenetwork.utilities.Bungee.Manager.Commands {
    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Args.length == 1 && (Args[0].equalsIgnoreCase("reload") || Args[0].equalsIgnoreCase("rl"))) {
            System.out.println(UNUtilities.prefix + " §eReloading Settings Manager...");
            UNUtilities.settings = new YamlBuilder("settings.yml");
            UNUtilities.prefix = id.universenetwork.utilities.Bungee.Manager.Settings.Settings(Settings.PREFIX);
            System.out.println(UNUtilities.prefix + " §aSettings Manager have been reloaded");
            System.out.println(UNUtilities.prefix + " §eReloading Data Manager...");
            UNUtilities.data = new YamlBuilder("data.yml");
            System.out.println(UNUtilities.prefix + " §aData Manager has been reloaded");
            Sender.sendMessage(id.universenetwork.utilities.Bungee.Manager.Settings.Settings(Settings.RELOAD));
        } else {
            sendTranslate(Sender, "&b&lU&e&lN&9&lUtilities");
            sendTranslate(Sender, "");
            sendTranslate(Sender, "&d/UniverseUtilitiesBungee reload");
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