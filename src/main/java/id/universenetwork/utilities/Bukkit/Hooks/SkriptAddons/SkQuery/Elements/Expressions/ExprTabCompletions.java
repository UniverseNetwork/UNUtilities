package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events.Bukkit.AttachedTabCompleteEvent;
import org.bukkit.event.Event;

import java.util.List;

import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("[tab] (completions|suggestions)")
public class ExprTabCompletions extends SimpleExpression<String> {
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(AttachedTabCompleteEvent.class)) {
            Skript.error("Tab completers can only be accessed from tab complete events.", SEMANTIC_ERROR);
            return false;
        }
        return true;
    }

    @Override
    protected String[] get(Event event) {
        List<String> completions = ((AttachedTabCompleteEvent) event).getResult();
        return completions.toArray(new String[0]);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "tab results";
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD)
            return asArray(String.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        String node = delta[0] == null ? "" : (String) delta[0];
        AttachedTabCompleteEvent event = ((AttachedTabCompleteEvent) e);
        if (node.toLowerCase().startsWith(event.getArgs()[event.getArgs().length - 1].toLowerCase()))
            event.getResult().add(node);
    }
}