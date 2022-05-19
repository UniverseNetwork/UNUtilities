package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Settings;
import id.universenetwork.utilities.Bungee.Utils.Color;
import id.universenetwork.utilities.Universal.Annotations.CommandProperties;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static id.universenetwork.utilities.Bungee.Enums.Whitelister.*;
import static id.universenetwork.utilities.Bungee.UNUtilities.data;

@CommandProperties(Name = "WhitelistBungee", Permission = "unutilities.command.whitelistbungee", PlayerOnly = false, Aliases = {"whitelistb", "bwhitelist", "bungeewhitelist"})
public class WhitelistBungee extends id.universenetwork.utilities.Bungee.Manager.Commands {
    List<String> p;

    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Settings.WBoolean(ENABLED)) {
            p = data.getStringList("whitelist.players");
            if (Args.length == 1) {
                boolean enabled = data.getBoolean("whitelist.enabled");
                if (Args[0].equalsIgnoreCase("on")) {
                    if (enabled) Sender.sendMessage(Settings.WString(AEM));
                    else {
                        data.set("whitelist.enabled", true);
                        Sender.sendMessage(Settings.WString(EM));
                    }
                } else if (Args[0].equalsIgnoreCase("off")) {
                    if (enabled) {
                        data.set("whitelist.enabled", false);
                        Sender.sendMessage(Settings.WString(DM));
                    } else Sender.sendMessage(Settings.WString(ADM));
                } else if (Args[0].equalsIgnoreCase("list")) {
                    if (p.isEmpty()) Sender.sendMessage(Settings.WString(LNWM));
                    else
                        Sender.sendMessage(StringUtils.replaceEach(Settings.WString(LM), new String[]{"%count%", "%player%"}, new String[]{Integer.toString(p.size()), StringUtils.replace(p.toString(), "]", "").replaceAll("\\[", "")}));
                } else sendUsage(Sender);
            } else if (Args.length == 2) {
                if (Args[0].equalsIgnoreCase("add")) {
                    if (p.contains(Args[1])) Sender.sendMessage(Settings.WString(AAM));
                    else {
                        p.add(Args[1]);
                        data.set("whitelist.players", p);
                        Sender.sendMessage(StringUtils.replace(Settings.WString(AM), "%player%", Args[1]));
                    }
                } else if (Args[0].equalsIgnoreCase("remove")) {
                    if (p.contains(Args[1])) {
                        p.remove(Args[1]);
                        data.set("whitelist.players", p);
                        Sender.sendMessage(StringUtils.replace(Settings.WString(RM), "%player%", Args[1]));
                    } else Sender.sendMessage(Settings.WString(NWM));
                } else sendUsage(Sender);
            } else sendUsage(Sender);
        }
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        java.util.List<String> arg = new ArrayList<>();
        if (Settings.WBoolean(ENABLED)) {
            if (Args.length == 1) {
                boolean enabled = data.getBoolean("whitelist.enabled");
                if (enabled) arg.add("off");
                else arg.add("on");
                arg.add("list");
                arg.add("add");
                arg.add("remove");
            } else if (Args.length == 2) {
                p = data.getStringList("whitelist.players");
                if (Args[0].equalsIgnoreCase("add")) {
                    Collection<ProxiedPlayer> listPlayers = ProxyServer.getInstance().getPlayers();
                    return listPlayers.stream().map(ProxiedPlayer::getName).collect(Collectors.toList());
                } else if (Args[0].equalsIgnoreCase("remove")) arg.addAll(p);
            }
        }
        return arg;
    }

    void sendUsage(CommandSender Sender) {
        List<String> l = Settings.WStringList(UM);
        for (String s : l) Sender.sendMessage(Color.Translator(s));
    }
}