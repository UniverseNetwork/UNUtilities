package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Projectile.ItemProjectile;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Patterns("make %livingentitys% shoot [an] item projectile [of] %itemtype% [at speed %number%]")
public class EffShootItemProjectile extends Effect {
    Expression<LivingEntity> shooters;
    Expression<ItemType> itemtype;
    Expression<Number> velocity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        shooters = (Expression<LivingEntity>) expressions[0];
        itemtype = (Expression<ItemType>) expressions[1];
        velocity = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    protected void execute(Event event) {
        if (itemtype == null || shooters == null) return;
        ItemType itemtype = this.itemtype.getSingle(event);
        float velocity = this.velocity != null ? this.velocity.getSingle(event).floatValue() : 1F;
        if (velocity <= 0) velocity = 1;
        ItemProjectile projectile = new ItemProjectile(itemtype);
        for (LivingEntity shooter : shooters.getArray(event)) {
            Location location = shooter.getLocation();
            projectile.shoot(shooter, location.getDirection().multiply(velocity));
        }
    }

    @Override
    public String toString(Event event, boolean debug) {
        if (debug) return "item projectile";
        return "item projectile " + itemtype.toString(event, debug) + " from " + shooters.toString(event, debug);
    }
}