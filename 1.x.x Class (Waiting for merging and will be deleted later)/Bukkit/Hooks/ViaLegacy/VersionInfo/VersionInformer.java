package id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.VersionInfo;

import id.universenetwork.utilities.bukkit.manager.Config;
import id.universenetwork.utilities.bukkit.utils.Color;
import org.bukkit.Bukkit;

import static com.viaversion.viaversion.api.Via.getAPI;
import static id.universenetwork.utilities.bukkit.Enums.ViaLegacy.*;

public class VersionInformer implements org.bukkit.event.Listener {
    final String message;
    final int maxVersion;

    public VersionInformer() {
        message = Color.Translate(Config.VLString(VERSIONINFO_MSG)).replace("%version%", Bukkit.getVersion().split(" ")[2].replace(")", ""));
        maxVersion = Config.VLInt(VERSIONINFO_MAX_VERSION);
        String interval = Config.VLString(VERSIONINFO_INTERVAL);
        if (interval.equalsIgnoreCase("JOIN"))
            id.universenetwork.utilities.bukkit.libraries.InfinityLib.Common.Events.registerListeners(this);
        else {
            long ticks = Long.parseLong(interval);
            Bukkit.getScheduler().runTaskTimer(id.universenetwork.utilities.bukkit.UNUtilities.plugin, () -> Bukkit.getOnlinePlayers().forEach(player -> {
                int version = getAPI().getPlayerVersion(player);
                if (version > maxVersion) return;
                player.sendMessage(message);
            }), ticks, ticks);
        }
    }

    @org.bukkit.event.EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        int version = getAPI().getPlayerVersion(e.getPlayer());
        if (version > maxVersion) return;
        e.getPlayer().sendMessage(message);
    }
}