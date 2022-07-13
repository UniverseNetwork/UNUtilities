package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import org.bukkit.entity.Entity;

@ch.njol.skript.doc.Name("In Lava")
@ch.njol.skript.doc.Description({"Checks if the specified entity/entities is/are in lava."})
@ch.njol.skript.doc.Examples({"if player is in lava:"})
public class CondInLava extends ch.njol.skript.conditions.base.PropertyCondition<Entity> {
    static {
        register(CondInLava.class, "in lava", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInLava();
    }

    @Override
    protected String getPropertyName() {
        return "in Lava";
    }
}