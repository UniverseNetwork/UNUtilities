package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Name("Make Damage")
@Description("Cause entities to damage each other. If you cause hostile mobs to attack or be attacked, they will become aggroed on the last entity that they hit or got hit by.")
@Examples("command /massacre:;->trigger:;->->make all pigs damage all cows by 1")
@Patterns("(make|force) %livingentities% [to] damage %livingentities% by %number%")
public class EffMakeDamage extends Effect {
    Expression<LivingEntity> attackers, victims;
    Expression<Number> number;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        attackers = (Expression<LivingEntity>) expressions[0];
        victims = (Expression<LivingEntity>) expressions[1];
        number = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        Number damage = number.getSingle(event);
        if (damage == null) return;
        for (LivingEntity victim : victims.getArray(event))
            for (LivingEntity attacker : attackers.getArray(event)) {
                victim.damage(damage.doubleValue(), attacker);
                if (attacker instanceof Creature) ((Creature) attacker).setTarget(victim);
            }
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "force damage on " + victims.toString(event, debug) + " from " + attackers.toString(event, debug);
    }
}