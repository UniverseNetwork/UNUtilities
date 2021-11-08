package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import org.bukkit.entity.Entity;

@ch.njol.skript.doc.Name("From Spawner")
@ch.njol.skript.doc.Description({"Checks if an entity is from a spawner."})
@ch.njol.skript.doc.Examples({"if event-entity is from a spawner:"})
public class CondFromSpawner extends ch.njol.skript.conditions.base.PropertyCondition<Entity> {
    static {
        register(CondFromSpawner.class, "from a spawner", "entities");
    }

    @Override
    public boolean check(final Entity e) {
        return e.fromMobSpawner();
    }

    @Override
    protected String getPropertyName() {
        return "from a spawner";
    }
}