package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@UsePropertyPatterns
@PropertyFrom("player")
@PropertyTo("(current|open) inventory")
public class ExprOpenInventory extends SimplePropertyExpression<Player, Inventory> {
    @Override
    protected String getPropertyName() {
        return "open inventory";
    }

    @Override
    public Inventory convert(Player player) {
        return player.getOpenInventory().getTopInventory();
    }

    @Override
    public Class<? extends Inventory> getReturnType() {
        return Inventory.class;
    }
}