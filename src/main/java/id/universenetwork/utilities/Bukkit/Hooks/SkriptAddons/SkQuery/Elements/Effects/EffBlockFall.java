package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.effects.EffSpawn;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.lang.reflect.InvocationTargetException;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Reflection.getOBCClass;

@SuppressWarnings("deprecation")
@Name("Spawn Falling Block")
@Description("Summons falling blocks with modifiable properties.")
@Examples({"on place of dirt:", "	cancel event", "	spawn falling block of dirt at block"})
@Patterns({"spawn falling block of %itemtype% at %locations%", "spawn damaging falling block of %itemtype% at %locations%", "spawn undroppable falling block of %itemtype% at %locations%", "spawn damaging undroppable falling block of %itemtype% at %locations%", "spawn undroppable damaging falling block of %itemtype% at %locations%"})
public class EffBlockFall extends Effect {
    Expression<Location> locations;
    Expression<ItemType> type;
    boolean breaks, damages;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        type = (Expression<ItemType>) expressions[0];
        locations = (Expression<Location>) expressions[1];
        damages = (matchedPattern == 1 || matchedPattern == 3 || matchedPattern == 4);
        breaks = (matchedPattern == 2 || matchedPattern == 3 || matchedPattern == 4);
        return true;
    }

    @Override
    protected void execute(Event event) {
        ItemType item = type.getSingle(event);
        if (item == null) return;
        for (Location location : locations.getArray(event))
            for (ItemStack itemstack : item.getAll()) {
                FallingBlock block;
                if (!Skript.methodExists(Material.class, "createBlockData"))
                    block = location.getWorld().spawnFallingBlock(location, new MaterialData(itemstack.getType(), (byte) itemstack.getDurability()));
                else block = location.getWorld().spawnFallingBlock(location, itemstack.getType().createBlockData());
                EffSpawn.lastSpawned = block;
                if (damages) try {
                    Object craftSand = getOBCClass("entity.CraftFallingSand").getMethod("getHandle").invoke(block);
                    craftSand.getClass().getMethod("a", boolean.class).invoke(craftSand, true);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (breaks) block.setDropItem(false);
            }
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "falling block " + type.toString(event, debug);
    }
}