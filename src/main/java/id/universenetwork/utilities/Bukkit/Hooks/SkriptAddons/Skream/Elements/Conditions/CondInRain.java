package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import org.bukkit.entity.Entity;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("In Rain")
@ch.njol.skript.doc.Description({"Checks if the specified entity/entities is/are in rain."})
@ch.njol.skript.doc.Examples({"if player is in rain:"})
public class CondInRain extends ch.njol.skript.conditions.base.PropertyCondition<Entity> {
    static {
        register(CondInRain.class, "in rain", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.isInRain();
    }

    @Override
    protected String getPropertyName() {
        return "in Rain";
    }
}