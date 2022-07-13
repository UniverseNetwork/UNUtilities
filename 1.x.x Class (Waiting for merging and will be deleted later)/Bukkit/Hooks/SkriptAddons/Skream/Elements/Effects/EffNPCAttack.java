package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Name("Make NPC Attack")
@Description({"Makes the npc start pathfinding to the specified livingentity & start attacking when they get close enough.", "NOTE: You can stop this from occurring by using the CancelNPCPath effect."})
@Examples({"make npc last spawned npc attack player"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffNPCAttack extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffNPCAttack.class, "make npc %integers% (hit|attack) %livingentity%");
    }

    Expression<Integer> id;
    Expression<LivingEntity> entity;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.entity = (Expression<LivingEntity>) expressions[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Make npc attack effect with expression integer: " + id.toString(event, debug) + " and expression livingentity " + entity.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.getNavigator().setTarget(entity.getSingle(event), true);
        }
    }
}