package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic;

public class Events implements org.bukkit.event.Listener {
    @org.bukkit.event.EventHandler
    void onQuit(org.bukkit.event.player.PlayerQuitEvent e) {
        SkMusic.songPlayers.remove(e.getPlayer());
    }
}