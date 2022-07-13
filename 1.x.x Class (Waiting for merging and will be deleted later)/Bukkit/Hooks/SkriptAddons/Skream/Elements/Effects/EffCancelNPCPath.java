package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@Name("Cancel NPC Pathfinding")
@Description({"Stops the NPC from completing its pathfinding goal.", "NOTE: This can also be used to stop an NPC from attacking."})
@Examples({"cancel pathfinding for npc last spawned npc"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffCancelNPCPath extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffCancelNPCPath.class, "(cancel|delete) (path[ |-]finding|[the ]path) for npc %integers%");
    }

    Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Cancel npc path with expression integer: " + id.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.getNavigator().cancelNavigation();
        }
    }
}