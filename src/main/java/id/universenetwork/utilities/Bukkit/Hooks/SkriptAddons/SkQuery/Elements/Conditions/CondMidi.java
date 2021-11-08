package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Midi is playing")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description("Checks whether or not a midi ID is playing.")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns({"midi [ids] %strings% (are|is) playing", "midi [ids] %strings% (are|is)(n't| not) playing"})
public class CondMidi extends ch.njol.skript.lang.Condition {
    Expression<String> ID;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        ID = (Expression<String>) expressions[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        return ID.check(event, id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Note.MidiUtil::isPlaying, isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "midi " + ID.toString(event, debug) + " is playing";
    }
}