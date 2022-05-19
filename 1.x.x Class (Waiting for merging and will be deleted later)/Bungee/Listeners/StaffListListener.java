package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.StaffList;
import id.universenetwork.utilities.Bungee.Manager.Settings;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static net.md_5.bungee.api.ProxyServer.getInstance;
import static org.apache.commons.lang.StringUtils.replace;

public class StaffListListener implements Listener {
    @EventHandler
    public void onJoin(PostLoginEvent e) {
        if (Settings.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist"))
                    p.sendMessage(replace(Settings.SLString(StaffList.JM), "%player%", e.getPlayer().getName()));
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        if (Settings.SLBoolean(StaffList.ENABLED) && e.getPlayer().hasPermission("unutilities.command.stafflist"))
            for (ProxiedPlayer p : getInstance().getPlayers())
                if (p.hasPermission("unutilities.command.stafflist"))
                    p.sendMessage(replace(Settings.SLString(StaffList.LM), "%player%", e.getPlayer().getName()));
    }
}