package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Make NPC Pathfind")
@Description({"Makes the specified NPC start pathfinding to the specified location.", "NOTE: If the NPC is unable to naturally walk to the location, it will teleport!"})
@Examples({"make npc last spawned npc pathfind to location of target block"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffNPCPath extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffNPCPath.class, "make npc %integers% path[ |-]find to [the] [location [at]] %location% [with speed %number%]");
    }

    Expression<Integer> id;
    Expression<Location> loc;
    Expression<Number> speed;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        this.speed = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Make npc path effect with expression integer: " + id.toString(event, debug) + " and expression location " + loc.toString(event, debug) + " and expression number " + speed.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        Number n;
        if (speed != null) n = speed.getSingle(event);
        else n = 1;
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.getNavigator().getDefaultParameters().baseSpeed(n.floatValue());
            npc.getNavigator().setTarget(loc.getSingle(event));
        }
    }
}