package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@Name("Lookclose trait of NPC")
@Description({"Allows you to set the lookclose trait of the specified npc to true/false"})
@Examples({"set lookclose trait of npc last spawned npc to true"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffLookCloseNPC extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffLookCloseNPC.class, "set look[ |-]close trait of npc %integers% to %boolean%");
    }

    Expression<Integer> id;
    Expression<Boolean> bool;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.bool = (Expression<Boolean>) expressions[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Look-close trait of npc effect with expression integer: " + id.toString(event, debug) + " and expression boolean " + bool.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        for (Integer i : id.getAll(event)) {
            npc = reg.getById(i);
            npc.getOrAddTrait(net.citizensnpcs.trait.LookClose.class).lookClose(bool.getSingle(event));
        }
    }
}