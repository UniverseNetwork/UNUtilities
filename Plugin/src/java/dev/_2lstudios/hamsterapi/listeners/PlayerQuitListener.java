package dev._2lstudios.hamsterapi.listeners;

import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.event.EventPriority.HIGHEST;

public class PlayerQuitListener implements Listener {
    final HamsterPlayerManager hamsterPlayerManager;

    public PlayerQuitListener(HamsterPlayerManager hamsterPlayerManager) {
        this.hamsterPlayerManager = hamsterPlayerManager;
    }

    @EventHandler(priority = HIGHEST, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent e) {
        hamsterPlayerManager.remove(e.getPlayer());
    }
}