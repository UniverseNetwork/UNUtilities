package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@Name("Despawn NPC")
@Description({"Removes the specified NPC from the world (doesn't delete data completely) but allows it to be respawned if needed (See Respawn NPC effect)"})
@Examples({"set {id} to last spawned npc", "despawn npc {id}"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffDespawnNPC extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffDespawnNPC.class, "de[ |-]spawn npc %integers%");
    }

    Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int m, ch.njol.util.Kleenean i, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        this.id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean d) {
        return "Despawn npc effect with expression integer: " + id.toString(e, d);
    }

    @Override
    protected void execute(Event e) {
        net.citizensnpcs.api.npc.NPCRegistry r = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC n;
        for (Integer i : id.getAll(e)) {
            n = r.getById(i);
            n.despawn();
        }
    }
}