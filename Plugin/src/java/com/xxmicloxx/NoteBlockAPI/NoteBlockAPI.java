package com.xxmicloxx.NoteBlockAPI;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class; contains methods for playing and adjusting songs for players
 */
public class NoteBlockAPI {
    static NoteBlockAPI API;
    final Map<UUID, ArrayList<SongPlayer>> playingSongs = new ConcurrentHashMap<>();
    final Map<UUID, Byte> playerVolume = new ConcurrentHashMap<>();
    boolean disabling = false;
    final HashMap<Plugin, Boolean> dependentPlugins = new HashMap<>();

    /**
     * Returns true if a Player is currently receiving a song
     *
     * @return is receiving a song
     */
    public static boolean isReceivingSong(Player player) {
        return isReceivingSong(player.getUniqueId());
    }

    /**
     * Returns true if a Player with specified UUID is currently receiving a song
     *
     * @return is receiving a song
     */
    public static boolean isReceivingSong(UUID uuid) {
        ArrayList<SongPlayer> songs = API.playingSongs.get(uuid);
        return (songs != null && !songs.isEmpty());
    }

    /**
     * Stops the song for a Player
     */
    public static void stopPlaying(Player player) {
        stopPlaying(player.getUniqueId());
    }

    /**
     * Stops the song for a Player
     */
    public static void stopPlaying(UUID uuid) {
        ArrayList<SongPlayer> songs = API.playingSongs.get(uuid);
        if (songs == null) return;
        for (SongPlayer songPlayer : songs) songPlayer.removePlayer(uuid);
    }

    /**
     * Sets the volume for a given Player
     */
    public static void setPlayerVolume(Player player, byte volume) {
        setPlayerVolume(player.getUniqueId(), volume);
    }

    /**
     * Sets the volume for a given Player
     */
    public static void setPlayerVolume(UUID uuid, byte volume) {
        API.playerVolume.put(uuid, volume);
    }

    /**
     * Gets the volume for a given Player
     *
     * @return volume (byte)
     */
    public static byte getPlayerVolume(Player player) {
        return getPlayerVolume(player.getUniqueId());
    }

    /**
     * Gets the volume for a given Player
     *
     * @return volume (byte)
     */
    public static byte getPlayerVolume(UUID uuid) {
        return API.playerVolume.computeIfAbsent(uuid, k -> (byte) 100);
    }

    public static ArrayList<SongPlayer> getSongPlayersByPlayer(Player player) {
        return getSongPlayersByPlayer(player.getUniqueId());
    }

    public static ArrayList<SongPlayer> getSongPlayersByPlayer(UUID player) {
        return API.playingSongs.get(player);
    }

    public static void setSongPlayersByPlayer(Player player, ArrayList<SongPlayer> songs) {
        setSongPlayersByPlayer(player.getUniqueId(), songs);
    }

    public static void setSongPlayersByPlayer(UUID player, ArrayList<SongPlayer> songs) {
        API.playingSongs.put(player, songs);
    }

    public void onEnable() {
        API = this;
        for (Plugin pl : Bukkit.getServer().getPluginManager().getPlugins())
            if (pl.getDescription().getDepend().contains("NoteBlockAPI") || pl.getDescription().getSoftDepend().contains("NoteBlockAPI"))
                dependentPlugins.put(pl, false);
        Logger.info("&bSuccessfully initialized &eNoteBlockAPI");
    }

    public void onDisable() {
        disabling = true;
        Bukkit.getScheduler().cancelTasks(UNUtilities.plugin);
        Logger.info("&cSuccessfully declared &eNoteBlockAPI");
    }

    public BukkitTask doSync(Runnable runnable) {
        return Bukkit.getServer().getScheduler().runTask(UNUtilities.plugin, runnable);
    }

    public BukkitTask doAsync(Runnable runnable) {
        return Bukkit.getServer().getScheduler().runTaskAsynchronously(UNUtilities.plugin, runnable);
    }

    public boolean isDisabling() {
        return disabling;
    }

    public static NoteBlockAPI getAPI() {
        return API;
    }
}