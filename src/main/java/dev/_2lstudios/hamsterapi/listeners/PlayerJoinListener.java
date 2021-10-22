package dev._2lstudios.hamsterapi.listeners;

import dev._2lstudios.hamsterapi.HamsterAPI;
import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayer;
import dev._2lstudios.hamsterapi.hamsterplayer.HamsterPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.event.EventPriority.LOWEST;

public class PlayerJoinListener implements Listener {
    final HamsterPlayerManager hamsterPlayerManager;

    public PlayerJoinListener(final HamsterAPI hamsterAPI) {
        this.hamsterPlayerManager = hamsterAPI.getHamsterPlayerManager();
    }

    @EventHandler(priority = LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        final HamsterPlayer hamsterPlayer = hamsterPlayerManager.add(player);
        if (!hamsterPlayer.tryInject())
            getLogger().warning(prefix + " §eFailed to inject player " + player.getName() + " please contact 2LStudios for support about HamsterAPI as this can lead to vulnerabilities.");
    }
}