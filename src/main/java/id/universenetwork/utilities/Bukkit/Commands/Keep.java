package id.universenetwork.utilities.Bukkit.Commands;

import id.universenetwork.utilities.Bukkit.Enums.Features.PerPlayerKeeper;
import id.universenetwork.utilities.Bukkit.Manager.Commands;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import id.universenetwork.utilities.Bukkit.Manager.Data;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Color.sendTranslate;

public class Keep extends Commands {
    public Keep() {
        super("keep", "unutilities.command.keep", false);
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("inventory") || args[0].equalsIgnoreCase("inv")) {
                List<String> keepINV = Data.get().getStringList("keepINV");
                if (!keepINV.contains(sender.getName())) {
                    keepINV.add(sender.getName());
                    Data.set("keepINV", keepINV);
                    sendTranslate(sender, Config.PPKMessage(PerPlayerKeeper.INVMESSAGE).replaceAll("%mode%", "&aON"));
                } else {
                    keepINV.remove(sender.getName());
                    Data.set("keepINV", keepINV);
                    sendTranslate(sender, Config.PPKMessage(PerPlayerKeeper.INVMESSAGE).replaceAll("%mode%", "&aOFF"));
                }
            } else if (args[0].equalsIgnoreCase("experience") || args[0].equalsIgnoreCase("xp")) {
                List<String> keepXP = Data.get().getStringList("keepXP");
                if (!keepXP.contains(sender.getName())) {
                    keepXP.add(sender.getName());
                    Data.set("keepXP", keepXP);
                    sendTranslate(sender, Config.PPKMessage(PerPlayerKeeper.XPMESSAGE).replaceAll("%mode%", "&aON"));
                } else {
                    keepXP.remove(sender.getName());
                    Data.set("keepXP", keepXP);
                    sendTranslate(sender, Config.PPKMessage(PerPlayerKeeper.XPMESSAGE).replaceAll("%mode%", "&aOFF"));
                }
            } else sendHelp(sender);
        } else sendHelp(sender);
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        if (args.length == 1) {
            List<String> arg = new ArrayList<>();
            arg.add("inventory");
            arg.add("experience");
            return arg;
        }
        return Collections.emptyList();
    }

    void sendHelp(CommandSender sender) {
        sendTranslate(sender, "&b&lU&e&lN&9&lUtilities");
        sendTranslate(sender, "");
        sendTranslate(sender, "&d/" + getName() + " inventory");
        sendTranslate(sender, "&d/" + getName() + " experience");
    }
}
