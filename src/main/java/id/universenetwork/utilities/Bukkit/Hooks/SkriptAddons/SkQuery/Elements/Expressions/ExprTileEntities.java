package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Event;

import java.util.ArrayList;

@UsePropertyPatterns
@PropertyFrom("chunks")
@PropertyTo("tile entities")
public class ExprTileEntities extends SimpleExpression<Block> {
    Expression<Chunk> chunk;

    @Override
    protected Block[] get(Event event) {
        ArrayList<Block> list = new ArrayList<>();
        for (Chunk c : chunk.getAll(event)) for (BlockState b : c.getTileEntities()) list.add(b.getBlock());
        return list.toArray(new Block[0]);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "tile entities";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        chunk = (Expression<Chunk>) expressions[0];
        return true;
    }
}