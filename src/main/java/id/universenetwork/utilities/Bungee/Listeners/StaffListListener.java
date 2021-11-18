package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.StaffList;
import id.universenetwork.utilities.Bungee.Manager.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.event.EventHandler;

import static net.md_5.bungee.api.ProxyServer.getInstance;

public class StaffListListener implements net.md_5.bungee.api.plugin.Listener {
    @EventHandler
    public void onJoin(net.md_5.bungee.api.event.PostLoginEvent e) {
        if (Config.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist"))
                    p.sendMessage(Config.SLString(StaffList.JM).replaceAll("%player%", e.getPlayer().getName()));
    }

    @EventHandler
    public void onLeave(net.md_5.bungee.api.event.PlayerDisconnectEvent e) {
        if (Config.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist"))
                    p.sendMessage(Config.SLString(StaffList.LM).replaceAll("%player%", e.getPlayer().getName()));
    }
}