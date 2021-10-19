package dev._2lstudios.hamsterapi.utils;

import org.bukkit.entity.Player;

import java.util.Collection;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class BukkitUtils {
    public static Player getRandomPlayer() {
        final Collection<? extends Player> players = getOnlinePlayers();
        if (!players.isEmpty()) {
            final int i = (int) ((players.size()) * Math.random());
            return players.toArray(new Player[0])[i];
        }
        return null;
    }
}
