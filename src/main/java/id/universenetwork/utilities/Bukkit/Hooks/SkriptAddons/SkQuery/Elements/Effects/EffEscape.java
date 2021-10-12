package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

@Name("Escape Lines")
@Description("Skip the execution of a certain number of lines.")
@Examples("on script load:;->escape 1;->stop;->message \"Stop avoided!\" to console")
@Patterns("escape %number% [(level[s]|line[s])]")
public class EffEscape extends Effect {
    Expression<Number> escape;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        escape = (Expression<Number>) expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected TriggerItem walk(Event event) {
        debug(event, false);
        Number lines = escape.getSingle(event);
        if (lines == null) return null;
        TriggerItem item = getNext();
        for (int i = 0; i < lines.intValue(); i++) {
            if (item == null) return null;
            item = item.getNext();
        }
        return item;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "escape " + escape.toString(event, debug);
    }
}