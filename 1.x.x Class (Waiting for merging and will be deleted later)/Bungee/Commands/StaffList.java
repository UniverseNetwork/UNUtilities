package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Settings;
import id.universenetwork.utilities.Universal.Annotations.CommandProperties;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static id.universenetwork.utilities.Bungee.Enums.StaffList.DM;
import static id.universenetwork.utilities.Bungee.Enums.StaffList.ENABLED;

@CommandProperties(Name = "StaffList", Permission = "unutilities.command.stafflist", PlayerOnly = false, Aliases = {"unistaff", "unistafflist", "sl", "universestafflist", "ustafflist"})
public class StaffList extends id.universenetwork.utilities.Bungee.Manager.Commands {
    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Settings.SLBoolean(ENABLED)) {
            Collection<ProxiedPlayer> ap = ProxyServer.getInstance().getPlayers();
            ArrayList<ProxiedPlayer> fp = new ArrayList<>();
            for (ProxiedPlayer p : ap) if (p.hasPermission("unutilities.command.stafflist")) fp.add(p);
            Sender.sendMessage("§bUniv§eerse §aStaff List §8(§e" + fp.size() + "§8)");
            for (ProxiedPlayer p : fp)
                Sender.sendMessage("§e" + p.getName() + " §8- §a" + p.getServer().getInfo().getName());
        } else Sender.sendMessage(Settings.SLString(DM));
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        return Collections.emptyList();
    }
}