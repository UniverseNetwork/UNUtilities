package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffectType;

@ch.njol.skript.doc.Name("Entity has Potion")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description("Checks whether or not an entity has a certain potion effect.")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns({"%livingentities% (has|have) [potion [effect]] %potioneffecttypes%", "%livingentities% (doesn't|does not|do not|don't) have [potion [effect]] %potioneffecttypes%"})
public class CondHasPotion extends ch.njol.skript.lang.Condition {
    Expression<PotionEffectType> effects;
    Expression<LivingEntity> entities;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        entities = (Expression<LivingEntity>) expressions[0];
        effects = (Expression<PotionEffectType>) expressions[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        return entities.check(event, entity -> effects.check(event, entity::hasPotionEffect), isNegated());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return PropertyCondition.toString(this, PropertyCondition.PropertyType.HAVE, event, debug, entities, "effects " + effects.toString(event, debug));
    }
}