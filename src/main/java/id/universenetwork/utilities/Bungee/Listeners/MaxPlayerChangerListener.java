package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.Features.MaxPlayerChangerCommand;

import static id.universenetwork.utilities.Bungee.Manager.Config.MPCCBoolean;

public class MaxPlayerChangerListener implements net.md_5.bungee.api.plugin.Listener {
    @net.md_5.bungee.event.EventHandler
    public void onProxyPing(net.md_5.bungee.api.event.ProxyPingEvent e) {
        if (MPCCBoolean(MaxPlayerChangerCommand.ENABLED) && MPCCBoolean(MaxPlayerChangerCommand.USP))
            e.getResponse().getPlayers().setMax(id.universenetwork.utilities.Bungee.UNUtilities.plugin.getProxy().getConfig().getPlayerLimit());
    }
}