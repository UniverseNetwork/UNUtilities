package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.trait.trait.Equipment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@Name("Equip NPC")
@Description({"Allows you to equip an npc with an itemstack. This is used for armor."})
@Examples({"equip npc last spawned npc with iron leggings"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffEquipNPC extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffEquipNPC.class, "equip npc %integers% with %itemstack%");
    }

    Expression<Integer> id;
    Expression<ItemStack> stack;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.stack = (Expression<ItemStack>) expressions[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Equip npc effect with expression integer: " + id.toString(event, debug) + " and expression boolean " + stack.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        Equipment.EquipmentSlot slot;
        // Switch is from skRayFall
        switch (stack.getSingle(event).getType()) {
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case NETHERITE_LEGGINGS:
                slot = Equipment.EquipmentSlot.LEGGINGS;
                break;
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case NETHERITE_CHESTPLATE:
                slot = Equipment.EquipmentSlot.CHESTPLATE;
                break;
            case LEATHER_HELMET:
            case IRON_HELMET:
            case GOLDEN_HELMET:
            case CHAINMAIL_HELMET:
            case DIAMOND_HELMET:
            case NETHERITE_HELMET:
                slot = Equipment.EquipmentSlot.HELMET;
                break;
            default:
                slot = Equipment.EquipmentSlot.BOOTS;
                break;
        }
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.getOrAddTrait(Equipment.class).set(slot, stack.getSingle(event));
        }
    }
}