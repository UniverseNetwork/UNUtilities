package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Note.MidiUtil;
import org.bukkit.event.Event;

@Name("Midi is playing")
@Description("Checks whether or not a midi ID is playing.")
@Patterns({"midi [ids] %strings% (are|is) playing", "midi [ids] %strings% (are|is)(n't| not) playing"})
public class CondMidi extends Condition {
    Expression<String> ID;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        ID = (Expression<String>) expressions[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        return ID.check(event, MidiUtil::isPlaying, isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "midi " + ID.toString(event, debug) + " is playing";
    }
}