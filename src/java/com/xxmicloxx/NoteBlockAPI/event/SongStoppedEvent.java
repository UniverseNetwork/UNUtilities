package com.xxmicloxx.NoteBlockAPI.event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


/**
 * Called whenever a SongPlayer is stopped
 *
 * @see SongPlayer
 */
public class SongStoppedEvent extends Event {
    static final HandlerList handlers = new HandlerList();
    final SongPlayer songPlayer;

    public SongStoppedEvent(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Returns SongPlayer which is now stopped
     *
     * @return SongPlayer
     */
    public SongPlayer getSongPlayer() {
        return songPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}