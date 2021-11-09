package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Name("Make NPC Follow")
@Description({"Makes the npc continuously pathfind (follow) to the specified livingentity.", "NOTE: You can stop this from occurring by using the CancelNPCPath effect."})
@Examples({"make npc last spawned npc follow player"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffNPCFollow extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffNPCFollow.class, "make npc %integers% follow %livingentity% [with speed %number%]");
    }

    Expression<Integer> id;
    Expression<LivingEntity> entity;
    Expression<Number> speed;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.entity = (Expression<LivingEntity>) expressions[1];
        this.speed = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Make npc follow effect with expression integer: " + id.toString(event, debug) + " and expression livingentity " + entity.toString(event, debug) + " and expression number " + speed.toString(event, debug);
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
            npc.getNavigator().setTarget(entity.getSingle(event), false);
        }
    }
}