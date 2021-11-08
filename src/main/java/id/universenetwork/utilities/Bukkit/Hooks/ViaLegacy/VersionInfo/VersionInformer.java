package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.VersionInfo;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import org.bukkit.Bukkit;

import static com.viaversion.viaversion.api.Via.getAPI;
import static id.universenetwork.utilities.Bukkit.Enums.Features.ViaLegacy.*;

public class VersionInformer implements org.bukkit.event.Listener {
    final String message;
    final int maxVersion;

    public VersionInformer() {
        message = id.universenetwork.utilities.Bukkit.Manager.Color.Translator(Config.VLString(VERSIONINFO_MSG)).replace("%version%", Bukkit.getVersion().split(" ")[2].replace(")", ""));
        maxVersion = Config.VLInt(VERSIONINFO_MAX_VERSION);
        String interval = Config.VLString(VERSIONINFO_INTERVAL);
        if (interval.equalsIgnoreCase("JOIN"))
            id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this);
        else {
            long ticks = Long.parseLong(interval);
            Bukkit.getScheduler().runTaskTimer(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, () -> Bukkit.getOnlinePlayers().forEach(player -> {
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