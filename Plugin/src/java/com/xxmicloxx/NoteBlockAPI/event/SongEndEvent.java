package com.xxmicloxx.NoteBlockAPI.event;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a Song ends
 * or when no players are listening and auto destroy is enabled
 *
 * @see SongPlayer
 */
public class SongEndEvent extends Event {
    static final HandlerList handlers = new HandlerList();
    final SongPlayer song;

    public SongEndEvent(SongPlayer song) {
        this.song = song;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Returns SongPlayer which {@link Song} ends
     *
     * @return SongPlayer
     */
    public SongPlayer getSongPlayer() {
        return song;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}