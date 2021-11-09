package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@Name("Delete NPC")
@Description({"Deletes an NPC's data and completely removes it from the server."})
@Examples("delete npc last spawned npc")
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffDeleteNPC extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffDeleteNPC.class, "(delete|destroy) npc %integers%");
    }

    Expression<Integer> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean d) {
        return "Delete npc effect with expression integer: " + id.toString(e, d);
    }

    @Override
    protected void execute(Event e) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        net.citizensnpcs.api.npc.NPC npc;
        for (Integer i : id.getAll(e)) {
            npc = reg.getById(i);
            npc.destroy();
        }
    }
}