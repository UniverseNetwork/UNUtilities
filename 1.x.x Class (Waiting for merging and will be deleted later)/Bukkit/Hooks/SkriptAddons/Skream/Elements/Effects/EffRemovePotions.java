package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Remove All Potion Effects")
@ch.njol.skript.doc.Description({"Removes/deletes all potion effects from an entity."})
@ch.njol.skript.doc.Examples("remove all potion effects from player")
@ch.njol.skript.doc.Since("1.0")
public class EffRemovePotions extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffRemovePotions.class, "(remove|delete) all potion effects from %livingentities%");
    }

    Expression<LivingEntity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.entity = (Expression<LivingEntity>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Removes all potion effects from living entities.";
    }

    @Nullable
    @Override
    protected void execute(Event event) {
        if (entity == null) return;
        for (LivingEntity e : entity.getAll(event))
            for (org.bukkit.potion.PotionEffect effect : e.getActivePotionEffects())
                e.removePotionEffect(effect.getType());
    }
}