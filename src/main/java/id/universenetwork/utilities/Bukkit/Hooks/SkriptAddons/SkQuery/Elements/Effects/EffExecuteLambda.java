package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions.ExprInput;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaEffect;
import org.bukkit.event.Event;

@Patterns("(do|execute) [%-number% time[s]] %lambda%")
public class EffExecuteLambda extends Effect {
    Expression<LambdaEffect> effect;
    Expression<Number> times;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        times = (Expression<Number>) expressions[0];
        effect = (Expression<LambdaEffect>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        LambdaEffect lambda = effect.getSingle(event);
        Number number = times.getSingle(event);
        if (lambda == null) return;
        if (number != null) for (int i = 0; i < number.intValue(); i++) {
            ExprInput.setInput(event, i);
            lambda.walk(event);
            ExprInput.removeInput(event);
        }
        else lambda.walk(event);
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "execute lambda " + effect.toString(event, debug);
    }
}