package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.doc.*;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Name("All NPCs")
@Description({"Returns a list containing the id of all npcs. (Loop-able)"})
@Examples({"broadcast \"%all npcs%\"", "set {_} to a random element out of all teams", "loop all teams:", "set glowing of npc all npcs to true"})
@Since("1.0")
@RequiredPlugins("Citizens")
public class ExprAllNPCS extends ch.njol.skript.lang.util.SimpleExpression<Integer> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprAllNPCS.class, Integer.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[all] npcs");
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of NPC ids";
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        ArrayList<Integer> npcs = new ArrayList<>();
        for (net.citizensnpcs.api.npc.NPC npc : net.citizensnpcs.api.CitizensAPI.getNPCRegistries().iterator().next().sorted())
            npcs.add(npc.getId());
        if (npcs.size() > 0) return npcs.toArray(new Integer[0]);
        else return null;
    }
}