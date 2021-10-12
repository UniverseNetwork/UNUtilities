package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.Markup;
import org.bukkit.event.Event;

@Name("Evaluate Input Effect")
@Description("Runs the input string relative to the calling trigger which invoked it.")
@Examples("command /effectcommand <text>:;->trigger:;->->evaluate argument")
@Patterns({"evaluate %string/markup%", "^%markup%"})
public class EffEvaluateEffect extends Effect {
    Expression<?> effect;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        effect = expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        String pre = null;
        Object object = effect.getSingle(event);
        if (object instanceof String) pre = (String) object;
        else if (object instanceof Markup) pre = object.toString();
        if (pre == null) return;
        ParserInstance instance = ParserInstance.get();
        instance.setCurrentEvent("this", event.getClass());
        Effect e = Effect.parse(pre, null);
        instance.deleteCurrentEvent();
        if (e == null) return;
        e.run(event);
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "evaluate " + effect.toString(event, debug);
    }
}