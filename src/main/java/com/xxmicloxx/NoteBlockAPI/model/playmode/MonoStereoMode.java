package com.xxmicloxx.NoteBlockAPI.model.playmode;

import com.xxmicloxx.NoteBlockAPI.model.*;
import com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils;
import com.xxmicloxx.NoteBlockAPI.utils.NoteUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils.playSound;
import static com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils.isCustomInstrument;

/**
 * Ignores panning of {@link Note} and {@link Layer} from nbs format and plays mono {@link Note} as fake stereo at fixed offset from {@link Player} head.
 */
public class MonoStereoMode extends ChannelMode {
    float distance = 2;

    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, float pitch) {
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - InstrumentUtils.getCustomInstrumentFirstIndex()];
            if (instrument.getSound() != null) {
                playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, distance);
                playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, -distance);
            } else {
                playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, distance);
                playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, -distance);
            }
        } else {
            playSound(player, location, InstrumentUtils.getInstrument(note.getInstrument()), soundCategory, volume, pitch, distance);
            playSound(player, location, InstrumentUtils.getInstrument(note.getInstrument()), soundCategory, volume, pitch, -distance);
        }
    }

    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, boolean doTranspose) {
        float pitch;
        if (doTranspose) pitch = NoteUtils.getPitchTransposed(note);
        else pitch = NoteUtils.getPitchInOctave(note);
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - InstrumentUtils.getCustomInstrumentFirstIndex()];
            if (!doTranspose) {
                playSound(player, location, InstrumentUtils.warpNameOutOfRange(instrument.getSoundFileName(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, distance);
                playSound(player, location, InstrumentUtils.warpNameOutOfRange(instrument.getSoundFileName(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, -distance);
            } else {
                if (instrument.getSound() != null) {
                    playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, distance);
                    playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, -distance);
                } else {
                    playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, distance);
                    playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, -distance);
                }
            }
        } else {
            if (NoteUtils.isOutOfRange(note.getKey(), note.getPitch()) && !doTranspose) {
                playSound(player, location, InstrumentUtils.warpNameOutOfRange(note.getInstrument(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, distance);
                playSound(player, location, InstrumentUtils.warpNameOutOfRange(note.getInstrument(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, -distance);
            } else {
                playSound(player, location, InstrumentUtils.getInstrument(note.getInstrument()), soundCategory, volume, pitch, distance);
                playSound(player, location, InstrumentUtils.getInstrument(note.getInstrument()), soundCategory, volume, pitch, -distance);
            }
        }
    }

    /**
     * Returns distance of {@link Note} from {@link Player}'s head.
     */
    public float getDistance() {
        return distance;
    }

    /**
     * Sets distance of {@link Note} from {@link Player}'s head.
     */
    public void setDistance(float distance) {
        this.distance = distance;
    }
}