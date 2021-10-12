package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Serialization.InventorySerialUtils.fromBase64;

@Name("Restore Inventory")
@Description("Restores a player's inventory to a backup. This effect is deprecated and you should use ((ExprInventorySerials)Inventory Serials) instead.")
@Patterns("restore %inventories% (to|from) %string%")
public class EffDeserializeInventory extends Effect {
    Expression<Inventory> inventories;
    Expression<String> serialize;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        inventories = (Expression<Inventory>) expressions[0];
        serialize = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        String toSerialize = serialize.getSingle(event);
        if (toSerialize == null) return;
        Inventory serialized = fromBase64(toSerialize);
        for (Inventory inventory : inventories.getArray(event)) inventory.setContents(serialized.getContents());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "deserialize inventory " + inventories.toString(event, debug);
    }
}