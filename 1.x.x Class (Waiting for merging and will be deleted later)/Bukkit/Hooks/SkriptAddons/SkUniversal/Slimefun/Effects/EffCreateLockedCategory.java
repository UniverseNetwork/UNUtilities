package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@ch.njol.skript.doc.Name("Slimefun - Create Locked Category")
@ch.njol.skript.doc.Description("Creates Slimefun locked category (it will not show up in the guide unless you create at least 1 item for it)")
@ch.njol.skript.doc.Examples("create locked slimefun category with ID \"cool_locked_stuff\" with menu item dirt named \"Cool Locked Stuff\" with priority 0 with required categories \"RESOURCES\"")
public class EffCreateLockedCategory extends ch.njol.skript.lang.Effect {
    static {
        Skript.registerEffect(EffCreateLockedCategory.class, "create [a] [new] locked [Slimefun] category [with ID] %string% with [menu] item %itemstack% with (level|priority|tier) %integer% with required categor(y|ies) %strings%");
    }

    Expression<String> id;
    Expression<ItemStack> item;
    Expression<Integer> level;
    Expression<String> itemGroups;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(ch.njol.skript.events.bukkit.SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create locked category effect in any event but on skript load.");
            return false;
        }
        id = (Expression<String>) e[0];
        item = (Expression<ItemStack>) e[1];
        level = (Expression<Integer>) e[2];
        itemGroups = (Expression<String>) e[3];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "create Slimefun category " + id.toString(e, b) + " with item " + item.toString(e, b) + " with level " + level.toString(e, b) + " with required categories " + itemGroups.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (id.getSingle(e) == null || item.getSingle(e) == null || level.getSingle(e) == null || itemGroups.getSingle(e) == null)
            return;
        java.util.List<NamespacedKey> requiredItemGroups = new java.util.ArrayList<>();
        for (String itemGroupID : itemGroups.getArray(e)) {
            io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup = SlimefunHook.getItemGroup(itemGroupID);
            if (itemGroup != null) requiredItemGroups.add(itemGroup.getKey());
        }
        new io.github.thebusybiscuit.slimefun4.api.items.groups.LockedItemGroup(new NamespacedKey(Skript.getInstance(), id.getSingle(e).toLowerCase()), item.getSingle(e), level.getSingle(e), requiredItemGroups.toArray(new NamespacedKey[0])).register(SlimefunHook.ADDON);
    }
}