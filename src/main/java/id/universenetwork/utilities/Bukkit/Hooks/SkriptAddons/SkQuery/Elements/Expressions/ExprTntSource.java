package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;

@UsePropertyPatterns
@PropertyFrom("entities")
@PropertyTo("(primer|fuse lighting piece of shit)")
public class ExprTntSource extends SimplePropertyExpression<Entity, Entity> {
    @Override
    protected String getPropertyName() {
        return "tnt source";
    }

    @Override
    public Entity convert(Entity entity) {
        return entity instanceof TNTPrimed ? ((TNTPrimed) entity).getSource() : null;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }
}