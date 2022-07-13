package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("Respawn NPC")
@Description({"Respawns an npc at the specified location if it has been despawned (see Despawn NPC effect)."})
@Examples({"set {id} to last spawned npc", "respawn npc {id} at player"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffRespawnNPC extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffRespawnNPC.class, "respawn npc %integers% at %location%");
    }

    Expression<Integer> id;
    Expression<Location> loc;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Respawn npc effect with expression integer: " + id.toString(event, debug) + " and with expression location: " + loc.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.spawn(loc.getSingle(event));
        }
    }
}