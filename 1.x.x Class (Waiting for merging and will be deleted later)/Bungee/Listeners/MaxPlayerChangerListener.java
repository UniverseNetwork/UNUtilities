package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.MaxPlayerChangerCommand;
import id.universenetwork.utilities.Bungee.UNUtilities;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static id.universenetwork.utilities.Bungee.Manager.Settings.MPCCBoolean;

public class MaxPlayerChangerListener implements Listener {
    @EventHandler
    public void onProxyPing(ProxyPingEvent e) {
        if (MPCCBoolean(MaxPlayerChangerCommand.ENABLED) && MPCCBoolean(MaxPlayerChangerCommand.USP))
            e.getResponse().getPlayers().setMax(UNUtilities.plugin.getProxy().getConfig().getPlayerLimit());
    }
}