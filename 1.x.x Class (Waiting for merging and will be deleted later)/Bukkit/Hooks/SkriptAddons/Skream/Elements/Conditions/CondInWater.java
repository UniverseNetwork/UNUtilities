package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import org.bukkit.entity.Entity;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("In Water")
@ch.njol.skript.doc.Description({"Checks if the specified entity/entities is/are in water."})
@ch.njol.skript.doc.Examples({"if player is in water:"})
public class CondInWater extends ch.njol.skript.conditions.base.PropertyCondition<Entity> {
    static {
        register(CondInWater.class, "in water", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInWater();
    }

    @Override
    protected String getPropertyName() {
        return "in Water";
    }
}