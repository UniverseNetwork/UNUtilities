package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Serialization.InventorySerialUtils;
import org.bukkit.entity.Player;

@UsePropertyPatterns
@PropertyFrom("player")
@PropertyTo("serialized inventory")
public class ExprSerial extends SimplePropertyExpression<Player, String> {
    @Override
    protected String getPropertyName() {
        return "serial";
    }

    @Override
    public String convert(Player player) {
        return InventorySerialUtils.toBase64(player.getInventory());
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}