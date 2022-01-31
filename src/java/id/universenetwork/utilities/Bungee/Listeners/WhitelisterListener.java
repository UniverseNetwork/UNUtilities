package id.universenetwork.utilities.Bungee.Listeners;

import id.universenetwork.utilities.Bungee.Enums.Whitelister;
import id.universenetwork.utilities.Bungee.Manager.Settings;

import static id.universenetwork.utilities.Bungee.UNUtilities.data;

public class WhitelisterListener implements net.md_5.bungee.api.plugin.Listener {
    @net.md_5.bungee.event.EventHandler
    public void onLogin(net.md_5.bungee.api.event.LoginEvent e) {
        if (!e.isCancelled() && Settings.WBoolean(Whitelister.ENABLED) && data.getBoolean("whitelist.enabled") && !data.getStringList("whitelist.players").contains(e.getConnection().getName())) {
            e.setCancelled(true);
            e.setCancelReason(Settings.WString(Whitelister.KM));
        }
    }
}