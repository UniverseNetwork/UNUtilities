package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Slimefun - Slimefun Item")
@ch.njol.skript.doc.Description("Returns a Slimefun item.")
@ch.njol.skript.doc.Examples({"give slimefun item \"CHEESE\" to player"})
public class ExprSlimefunItem extends ch.njol.skript.lang.util.SimpleExpression<ItemStack> {
    static {
        Skript.registerExpression(ExprSlimefunItem.class, ItemStack.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] Slimefun item [with ID] %string%");
    }

    Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the Slimefun item with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected ItemStack[] get(Event e) {
        if (id.getSingle(e) == null) return null;
        return new ItemStack[]{io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById(id.getSingle(e)).getItem()};
    }
}