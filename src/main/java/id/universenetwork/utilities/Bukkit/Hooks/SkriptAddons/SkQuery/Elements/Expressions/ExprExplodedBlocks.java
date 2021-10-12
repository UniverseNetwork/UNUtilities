package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

@Patterns("[the] (destroyed|exploded|boom boomed) blocks")
public class ExprExplodedBlocks extends SimpleExpression<Block> {
    @Override
    protected Block[] get(Event event) {
        List<Block> blockList = ((EntityExplodeEvent) event).blockList();
        return blockList.toArray(new Block[0]);
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
        return "boom boom blocks";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        if (!ParserInstance.get().isCurrentEvent(EntityExplodeEvent.class)) {
            Skript.error("Boom Boomed Blocks can only be used in an explode event.");
            return false;
        }
        return true;
    }
}