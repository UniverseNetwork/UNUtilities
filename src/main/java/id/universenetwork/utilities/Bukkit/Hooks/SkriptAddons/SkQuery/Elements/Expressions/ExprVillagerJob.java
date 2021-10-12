package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@UsePropertyPatterns
@PropertyFrom("entities")
@PropertyTo("villager (profession|job)")
public class ExprVillagerJob extends SimplePropertyExpression<Entity, Villager.Profession> {
    @Override
    protected String getPropertyName() {
        return "job";
    }

    @Override
    public Villager.Profession convert(Entity entity) {
        return entity instanceof Villager ? ((Villager) entity).getProfession() : null;
    }

    @Override
    public Class<? extends Villager.Profession> getReturnType() {
        return Villager.Profession.class;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET)
            return asArray(Villager.Profession.class);
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        Villager.Profession n = delta[0] == null ? Villager.Profession.FARMER : (Villager.Profession) delta[0];
        switch (mode) {
            case SET:
                for (Entity en : getExpr().getAll(e)) if (en instanceof Villager) ((Villager) en).setProfession(n);
                break;
            case RESET:
                for (Entity en : getExpr().getAll(e))
                    if (en instanceof Villager) ((Villager) en).setProfession(Villager.Profession.FARMER);
            case ADD:
            case REMOVE:
            case REMOVE_ALL:
            case DELETE:
                assert false;
        }
    }
}