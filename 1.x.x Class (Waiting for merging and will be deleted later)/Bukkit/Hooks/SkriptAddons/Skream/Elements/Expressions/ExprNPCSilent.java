package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Silent State of NPC")
@Description({"Sets/gets the silent state of the specified NPC."})
@Examples({"set silent state of npc last spawned npc to true", "broadcast \"%silent state of npc last spawned npc%\""})
@Since("1.0")
@RequiredPlugins("Citizens")
public class ExprNPCSilent extends ch.njol.skript.lang.util.SimpleExpression<Boolean> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprNPCSilent.class, Boolean.class, ch.njol.skript.lang.ExpressionType.COMBINED, "silent state of npc [with] [the] [id] %integers%");
    }

    Expression<Integer> id;

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        id = (Expression<Integer>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Silent state of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected Boolean[] get(Event event) {
        if (id.getSingle(event) != null)
            return new Boolean[]{CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).data().get(NPC.SILENT_METADATA)};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        if (id != null) for (Integer ids : id.getAll(event)) {
            NPC npc = CitizensAPI.getNPCRegistry().getById(ids);
            if (mode == Changer.ChangeMode.SET) npc.data().setPersistent(NPC.SILENT_METADATA, delta[0]);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) return ch.njol.util.coll.CollectionUtils.array(Boolean.class);
        return null;
    }
}