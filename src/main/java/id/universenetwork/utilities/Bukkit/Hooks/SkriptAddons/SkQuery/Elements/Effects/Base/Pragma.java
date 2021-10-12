package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import java.io.File;

public abstract class Pragma extends Effect {
    @Override
    protected void execute(Event event) {
    }

    @Override
    public String toString(Event event, boolean b) {
        return "pragma";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        register(ParserInstance.get().getCurrentScript().getFile(), parseResult);
        return true;
    }

    protected abstract void register(File executingScript, ParseResult parseResult);
}