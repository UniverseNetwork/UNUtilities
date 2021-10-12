package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus.SlotRule;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus.FormattedSlotManager.addRule;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Menus.FormattedSlotManager.removeRule;

@Name("Format Inventory Slot")
@Description("Formats a slot in the player's open inventory to do certain actions. This should be done directly after showing an inventory to the player.")
@Examples("command /construct:;->trigger:;->->open chest with 1 rows named \"&4My first test menu\" to player;->->format slot 0 of player with 5 of steak named \"Item 1\" to close then run \"say The first item was clicked! Menu Closed!\";->->format slot 2 of player with fire named \"Close Menu\" with lore \"I will close this menu.||Nothing more, nothing less.\" to close")
@Patterns({"format slot %number% of %players% with %itemstack% to close then run %string/lambda%", "format slot %number% of %players% with %itemstack% to run %string/lambda%", "format slot %number% of %players% with %itemstack% to close", "format slot %number% of %players% with %itemstack% to (be|act) unstealable", "unformat slot %number% of %players%"})
public class EffFormatSlot extends Effect {
    Expression<Player> targets;
    Expression<ItemStack> item;
    Expression<Number> slot;
    Expression<?> callback;
    int action;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        slot = (Expression<Number>) expressions[0];
        targets = (Expression<Player>) expressions[1];
        if (matchedPattern <= 3) item = (Expression<ItemStack>) expressions[2];
        if (matchedPattern <= 1) callback = expressions[3];
        action = matchedPattern;
        return true;
    }

    @Override
    protected void execute(Event event) {
        Number s = slot.getSingle(event);
        if (s == null) return;
        Object c;
        ItemStack i = null;
        SlotRule toClone;
        switch (action) {
            case 0:
                c = callback.getSingle(event);
                i = item.getSingle(event);
                if (c == null) return;
                toClone = new SlotRule(c, true);
                break;
            case 1:
                c = callback.getSingle(event);
                i = item.getSingle(event);
                if (c == null) return;
                toClone = new SlotRule(c, false);
                break;
            case 2:
                i = item.getSingle(event);
                if (i == null) return;
                toClone = new SlotRule(null, true);
                break;
            case 3:
                i = item.getSingle(event);
                toClone = new SlotRule(null, false);
                break;
            case 4:
                for (Player p : targets.getArray(event)) removeRule(p, s.intValue());
                return;
            default:
                return;
        }
        if (i != null) {
            for (Player p : targets.getArray(event))
                if (p.getOpenInventory().getType() != InventoryType.CRAFTING)
                    p.getOpenInventory().setItem(s.intValue(), i);
        }
        for (Player p : targets.getArray(event))
            if (p.getOpenInventory().getType() != InventoryType.CRAFTING)
                addRule(event, p, s.intValue(), toClone.clone());
    }

    @Override
    public String toString(Event event, boolean b) {
        return "format slot";
    }
}