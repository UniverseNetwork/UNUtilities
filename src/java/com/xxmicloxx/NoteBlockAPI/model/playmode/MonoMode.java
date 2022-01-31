package com.xxmicloxx.NoteBlockAPI.model.playmode;

import com.xxmicloxx.NoteBlockAPI.model.*;
import com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.xxmicloxx.NoteBlockAPI.utils.CompatibilityUtils.playSound;
import static com.xxmicloxx.NoteBlockAPI.utils.InstrumentUtils.*;
import static com.xxmicloxx.NoteBlockAPI.utils.NoteUtils.*;

/**
 * {@link Note} is played inside of {@link Player}'s head.
 */
public class MonoMode extends ChannelMode {
    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, float pitch) {
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - InstrumentUtils.getCustomInstrumentFirstIndex()];
            if (instrument.getSound() != null)
                playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, 0);
            else playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, 0);
        } else playSound(player, location, getInstrument(note.getInstrument()), soundCategory, volume, pitch, 0);
    }

    @Override
    public void play(Player player, Location location, Song song, Layer layer, Note note, SoundCategory soundCategory, float volume, boolean doTranspose) {
        float pitch;
        if (doTranspose) pitch = getPitchTransposed(note);
        else pitch = getPitchInOctave(note);
        if (isCustomInstrument(note.getInstrument())) {
            CustomInstrument instrument = song.getCustomInstruments()[note.getInstrument() - getCustomInstrumentFirstIndex()];
            if (!doTranspose)
                playSound(player, location, warpNameOutOfRange(instrument.getSoundFileName(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, 0);
            else {
                if (instrument.getSound() != null)
                    playSound(player, location, instrument.getSound(), soundCategory, volume, pitch, 0);
                else playSound(player, location, instrument.getSoundFileName(), soundCategory, volume, pitch, 0);
            }
        } else {
            if (isOutOfRange(note.getKey(), note.getPitch()) && !doTranspose)
                playSound(player, location, warpNameOutOfRange(note.getInstrument(), note.getKey(), note.getPitch()), soundCategory, volume, pitch, 0);
            else playSound(player, location, getInstrument(note.getInstrument()), soundCategory, volume, pitch, 0);
        }
    }
}