package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Arrays;

@UsePropertyPatterns
@PropertyFrom("worlds")
@PropertyTo("loaded chunks")
public class ExprLoadedChunks extends SimpleExpression<Chunk> {
    Expression<World> worlds;

    @Override
    protected Chunk[] get(Event event) {
        ArrayList<Chunk> chunks = new ArrayList<>();
        for (World w : worlds.getAll(event)) chunks.addAll(Arrays.asList(w.getLoadedChunks()));
        return chunks.toArray(new Chunk[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Chunk> getReturnType() {
        return Chunk.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "loaded chunks";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        worlds = (Expression<World>) expressions[0];
        return true;
    }
}