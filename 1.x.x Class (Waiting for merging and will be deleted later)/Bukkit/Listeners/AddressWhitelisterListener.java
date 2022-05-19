package id.universenetwork.utilities.Bukkit.Listeners;

import id.universenetwork.utilities.Bukkit.Enums.AddressWhitelister;
import id.universenetwork.utilities.Bukkit.Manager.Config;
import id.universenetwork.utilities.Bukkit.Manager.Proxy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class AddressWhitelisterListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (Config.AWBoolean(AddressWhitelister.ENABLED)) {
            String kickMSG = Config.AWMessage(AddressWhitelister.KICKMSG);
            String joinedAddress = e.getRealAddress().getHostAddress();
            String bungeeAddress = Proxy.getBungeeAddress();
            if (bungeeAddress.contains(";")) {
                String[] v5 = bungeeAddress.split(";");
                int v6 = v5.length;
                for (String address : v5) {
                    if (joinedAddress.equalsIgnoreCase(address)) return;
                }
            }

            if (!joinedAddress.equalsIgnoreCase(bungeeAddress)) {
                kickMSG = kickMSG.replaceAll("%enter%", "\n");
                e.disallow(Result.KICK_OTHER, kickMSG);
            }
        }
    }
}