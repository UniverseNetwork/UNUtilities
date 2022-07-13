package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Offhand of NPC")
@Description({"Sets/gets the item in the npc's offhand."})
@Examples({"set offhand slot of npc last spawned npc to dirt", "broadcast \"%offhand slot of npc last spawned npc%\""})
@Since("1.0")
@RequiredPlugins("Citizens")
public class ExprOffHandNPC extends ch.njol.skript.lang.util.SimpleExpression<ItemStack> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprOffHandNPC.class, ItemStack.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[item from [the]] off[ |-]hand [slot] of npc %integer%");
    }

    Expression<Integer> id;

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        id = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Off-hand slot of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event event) {
        if (id.getSingle(event) != null)
            return new ItemStack[]{CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getOrAddTrait(Equipment.class).get(Equipment.EquipmentSlot.OFF_HAND)};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) for (Integer ids : id.getAll(event)) {
            net.citizensnpcs.api.npc.NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.OFF_HAND, (ItemStack) delta[0]);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return ch.njol.util.coll.CollectionUtils.array(ItemStack.class);
        return null;
    }
}