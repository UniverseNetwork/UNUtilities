package com.xxmicloxx.NoteBlockAPI.model.playmode;

import com.xxmicloxx.NoteBlockAPI.model.*;
import com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils;
import com.xxmicloxx.NoteBlockAPI.utils.NoteUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils.playSound;
import static com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils.*;
import static com.xxmicloxx.NoteBlockAPI.utils.NoteUtils.getPitchInOctave;
import static com.xxmicloxx.NoteBlockAPI.utils.NoteUtils.getPitchTransposed;

/**
 * Uses panning for individual {@link Note} and {@link Layer} based on data from nbs file.
 */
public class StereoMode extends ChannelMode {
    float maxDistance = 2;
    ChannelMode fallbackChannelMode = null;

    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, float pitch) {
        if (song.isStereo() && fallbackChannelMode != null) {
            fallbackChannelMode.play(player, location, song, layer, note, soundCategory, volume, pitch);
            return;
        }
        float distance;
        if (layer.getPanning() == 100) distance = (note.getPanning() - 100) * maxDistance;
        else distance = ((layer.getPanning() - 100 + note.getPanning() - 100) / 200f) * maxDistance;
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - getCustomInstrumentFirstIndex()];
            if (instrument.getSound() != null)
                playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, distance);
            else playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, distance);
        } else
            playSound(player, location, getInstrument(note.getInstrument()), soundCategory, volume, pitch, distance);
    }

    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, boolean doTranspose) {
        if (song.isStereo() && fallbackChannelMode != null) {
            fallbackChannelMode.play(player, location, song, layer, note, soundCategory, volume, doTranspose);
            return;
        }
        float pitch;
        if (doTranspose) pitch = getPitchTransposed(note);
        else pitch = getPitchInOctave(note);
        float distance;
        if (layer.getPanning() == 100) distance = (note.getPanning() - 100) * maxDistance;
        else distance = ((layer.getPanning() - 100 + note.getPanning() - 100) / 200f) * maxDistance;
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - getCustomInstrumentFirstIndex()];
            if (!doTranspose)
                playSound(player, location, warpNameOutOfRange(instrument.getSoundFileName(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, distance);
            else {
                if (instrument.getSound() != null)
                    playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, distance);
                else
                    playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, distance);
            }
        } else {
            if (NoteUtils.isOutOfRange(note.getKey(), note.getPitch()) && !doTranspose)
                playSound(player, location, InstrumentUtils.warpNameOutOfRange(note.getInstrument(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, distance);
            else
                playSound(player, location, getInstrument(note.getInstrument()), soundCategory, volume, pitch, distance);
        }
    }

    /**
     * Returns scale of panning in blocks. {@link Note} with maximum left panning will be played this distance from {@link Player}'s head on left side.
     */
    public float getMaxDistance() {
        return maxDistance;
    }

    /**
     * Sets scale of panning in blocks. {@link Note} with maximum left panning will be played this distance from {@link Player}'s head on left side.
     */
    public void setMaxDistance(float maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * Returns fallback {@link ChannelMode} used when song is not stereo.
     *
     * @return ChannelMode or null when fallback ChannelMode is disabled
     */
    public ChannelMode getFallbackChannelMode() {
        return fallbackChannelMode;
    }

    /**
     * Sets fallback {@link ChannelMode} which is used when song is not stereo. Set to null to disable.
     *
     * @throws IllegalArgumentException if parameter is instance of StereoMode
     */
    public void setFallbackChannelMode(ChannelMode fallbackChannelMode) {
        if (fallbackChannelMode instanceof StereoMode)
            throw new IllegalArgumentException("Fallback ChannelMode can't be instance of StereoMode!");
        this.fallbackChannelMode = fallbackChannelMode;
    }
}