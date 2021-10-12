package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("[the] block at %number%, %number%, %number% in [chunk] %chunk%")
public class ExprBlockInChunk extends SimpleExpression<Block> {
    Expression<Number> xC, yC, zC;
    Expression<Chunk> chunk;

    @Override
    protected Block[] get(Event event) {
        Number x = xC.getSingle(event);
        Number y = yC.getSingle(event);
        Number z = zC.getSingle(event);
        Chunk c = chunk.getSingle(event);
        if (x == null || y == null || z == null || c == null) return null;
        return asArray(c.getBlock(x.intValue(), y.intValue(), z.intValue()));
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "block at";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        xC = (Expression<Number>) expressions[0];
        yC = (Expression<Number>) expressions[1];
        zC = (Expression<Number>) expressions[2];
        chunk = (Expression<Chunk>) expressions[3];
        return true;
    }
}