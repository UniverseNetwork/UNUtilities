package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

@Name("Set Block Without Updates")
@Description("Set blocks to another block without causing surrounding blocks to update.")
@Patterns("@noupdate::set %blocks% to %itemtype%")
public class EffSetBlock extends Effect {
    Expression<Block> blocks;
    Expression<ItemType> toSet;

    @Override
    protected void execute(Event event) {
        ItemType t = toSet.getSingle(event);
        if (t == null) return;
        for (Block b : blocks.getAll(event)) t.getBlock().setBlock(b, false);
    }

    @Override
    public String toString(Event event, boolean b) {
        return "true";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        blocks = (Expression<Block>) expressions[0];
        toSet = (Expression<ItemType>) expressions[1];
        return true;
    }
}