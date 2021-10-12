package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@Name("Client Block")
@Description("Make a player see a block as another type of block. If the client recieves an update to the block, it will revert.")
@Examples("on click:;->make player see clicked block as air")
@Patterns("make %players% see %blocks% as %itemtype%")
public class EffClientBlock extends Effect {
    Expression<Player> players;
    Expression<ItemType> item;
    Expression<Block> blocks;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        players = (Expression<Player>) expressions[0];
        blocks = (Expression<Block>) expressions[1];
        item = (Expression<ItemType>) expressions[2];
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(Event event) {
        ItemType type = item.getSingle(event);
        if (type == null) return;
        ItemStack itemstack = type.getRandom();
        Material material = itemstack.getType();
        if (!material.isBlock()) return;
        for (Player player : players.getArray(event))
            for (Block block : blocks.getArray(event))
                player.sendBlockChange(block.getLocation(), material, (byte) itemstack.getDurability());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "client block to " + players.toString(event, debug) + " changing to " + item.toString(event, debug);
    }
}