package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@Name("NPC Flight")
@Description({"Allows you to set the flight mode of the specified npc to false/true"})
@Examples("set flight mode of npc last spawned npc to true")
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffNPCFlight extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffNPCFlight.class, "set flight [mode] of npc %integer% to %boolean%");
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
        return "NPC flight effect with expression integer: " + id.toString(event, debug) + " and expression boolean: " + bool.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc = reg.getById(id.getSingle(event));
        npc.setFlyable(bool.getSingle(event));
    }
}