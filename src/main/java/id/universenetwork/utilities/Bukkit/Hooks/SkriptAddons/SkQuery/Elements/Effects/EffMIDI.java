package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.sound.midi.InvalidMidiDataException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Note.MidiUtil.playMidi;
import static org.bukkit.Bukkit.getLogger;

@Name("Play MIDI")
@Description("Plays a file with the extention .mid to a player.")
@Examples("on join:;->play midi \"login\" to player")
@Patterns({"play midi [from] %string% (for|to) %players% [at [tempo] %-number%] [with id %-string%]", "play midi from (website|link) %string% (for|to) %players% [at [tempo] %-number%] [with id %-string%]"})
public class EffMIDI extends Effect {
    Expression<String> midi, ID;
    Expression<Player> players;
    Expression<Number> tempo;
    boolean url;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        midi = (Expression<String>) expressions[0];
        players = (Expression<Player>) expressions[1];
        tempo = (Expression<Number>) expressions[2];
        ID = (Expression<String>) expressions[3];
        url = matchedPattern == 1;
        return true;
    }

    @Override
    protected void execute(Event event) {
        String track = midi.getSingle(event);
        if (track == null) return;
        File file = new File(Skript.getInstance().getDataFolder(), Skript.SCRIPTSFOLDER + File.separator + track + ".mid");
        if (!file.exists()) {
            file = new File(track);
            if (!file.exists() && !file.getName().endsWith(".mid")) {
                getLogger().warning("Could not find midi file " + track + ".mid");
                return;
            }
        }
        Number tempo = 1;
        if (this.tempo != null) tempo = this.tempo.getSingle(event);
        Player[] players = this.players.getArray(event);
        if (players == null) return;
        String ID = track;
        if (this.ID != null) ID = this.ID.getSingle(event);
        try {
            if (url) {
                URL url = new URL(track);
                playMidi(url.openStream(), tempo.floatValue(), ID, players);
            } else playMidi(file, tempo.floatValue(), ID, players);
        } catch (InvalidMidiDataException | IOException e) {
            Skript.exception(e, "Could not play midi file " + track);
        }
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "play midi " + midi.toString(event, debug) + " to " + players.toString(event, debug);
    }
}