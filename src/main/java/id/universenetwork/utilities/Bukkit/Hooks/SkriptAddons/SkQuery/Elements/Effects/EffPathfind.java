package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Examples;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.InvocationTargetException;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Reflection.getNMSClass;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Reflection.getOBCClass;

@Name("Pathfind")
@Description("Invoke the entity pathfinder. Allows entities to pathfind to a location unless they are distracted. Not all entities have pathfinders.")
@Examples("on click on cow:;->while clicked entity exists:;->->make clicked entity pathfind to player with speed;->->wait 1 tick")
@Patterns("make %livingentities% pathfind to %location% (with|at) speed %number%")
public class EffPathfind extends Effect {
    Expression<LivingEntity> entity;
    Expression<Location> loc;
    Expression<Number> speed;

    @Override
    protected void execute(Event event) {
        Location l = loc.getSingle(event);
        Number s = speed.getSingle(event);
        if (l == null || s == null) return;
        for (LivingEntity e : entity.getAll(event)) {
            if (e instanceof Player) continue;
            try {
                Object entityInsentient = getOBCClass("entity.CraftLivingEntity").getMethod("getHandle").invoke(e);
                Object navigation = getNMSClass("EntityInsentient").getMethod("getNavigation").invoke(entityInsentient);
                navigation.getClass().getMethod("a", double.class, double.class, double.class, double.class).invoke(navigation, l.getX(), l.getY(), l.getZ(), s.doubleValue());
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "pathfind";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        entity = (Expression<LivingEntity>) expressions[0];
        loc = (Expression<Location>) expressions[1];
        speed = (Expression<Number>) expressions[2];
        return true;
    }
}