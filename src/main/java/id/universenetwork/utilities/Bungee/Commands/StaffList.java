package id.universenetwork.utilities.Bungee.Commands;

import id.universenetwork.utilities.Bungee.Manager.Config;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.Collection;

import static id.universenetwork.utilities.Bungee.Enums.StaffList.DM;
import static id.universenetwork.utilities.Bungee.Enums.StaffList.ENABLED;

public class StaffList extends id.universenetwork.utilities.Bungee.Manager.Commands {
    public StaffList() {
        super("StaffList", "unutilities.command.stafflist", false, "unistaff", "unistafflist", "sl", "universestafflist", "ustafflist");
    }

    @Override
    public void Execute(CommandSender Sender, String[] Args) {
        if (Config.SLBoolean(ENABLED)) {
            Collection<ProxiedPlayer> ap = net.md_5.bungee.api.ProxyServer.getInstance().getPlayers();
            ArrayList<ProxiedPlayer> fp = new ArrayList<>();
            for (ProxiedPlayer p : ap) if (p.hasPermission("unutilities.command.stafflist")) fp.add(p);
            Sender.sendMessage("§bUniv§eerse §aStaff List §8(" + fp.size() + ")");
            for (ProxiedPlayer p : fp)
                Sender.sendMessage("§e" + p.getName() + " §8- §a" + p.getServer().getInfo().getName());
        } else Sender.sendMessage(Config.SLString(DM));
    }

    @Override
    public Iterable<String> TabComplete(CommandSender Sender, String[] Args) {
        return java.util.Collections.emptyList();
    }
}