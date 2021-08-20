package id.universenetwork.utilities.Bungee.Listeners;

import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;

public class ChangeSlotsListener implements Listener {
    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        e.getResponse().getPlayers().setMax(plugin.getProxy().getConfig().getPlayerLimit());
    }
}
