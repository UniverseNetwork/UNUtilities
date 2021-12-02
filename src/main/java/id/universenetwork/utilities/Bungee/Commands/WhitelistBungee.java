package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Settings;
import net.md_5.bungee.api.CommandSender;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static id.universenetwork.utilities.Bungee.Enums.Whitelister.*;
import static id.universenetwork.utilities.Bungee.UNUtilities.data;

@id.universenetwork.utilities.Universal.Annotations.CommandProperties(Name = "WhitelistBungee", Permission = "unutilities.command.whitelistbungee", PlayerOnly = false, Aliases = {"whitelistb", "bwhitelist", "bungeewhitelist"})
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
                }
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
                }
            }
        }
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        p = data.getStringList("whitelist.players");
        return java.util.Collections.emptyList();
    }
}