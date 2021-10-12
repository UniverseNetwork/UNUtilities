package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Note.MidiUtil.isPlaying;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Note.MidiUtil.stop;

@Name("Stop MIDI")
@Description("Stops a midi file that is playing.")
@Examples({"on join:", "	play midi \"login\" to player", "	wait 5 seconds", "	stop midi \"login\""})
@Patterns("stop midi[s] [[with] id] %strings%")
public class EffMIDIStop extends Effect {
    Expression<String> IDs;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int markedPattern, Kleenean kleenean, ParseResult parseResult) {
        IDs = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        for (String track : IDs.getArray(event)) {
            if (track == null) continue;
            if (isPlaying(track)) stop(track);
        }
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "stop midi " + IDs.toString(event, debug);
    }
}