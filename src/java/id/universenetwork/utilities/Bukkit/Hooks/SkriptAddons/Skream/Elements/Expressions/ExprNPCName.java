package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Name of NPC")
@Description({"Sets/gets the name of the specified npc."})
@Examples({"set name of npc last spawned npc to \"happypockets\"", "broadcast \"%name of npc last spawned npc%\""})
@Since("1.0")
@RequiredPlugins("Citizens")
public class ExprNPCName extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprNPCName.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "name of npc [with] [the] [id] %integer%");
    }

    Expression<Integer> id;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
        return "Name of npc expression with expression integer: " + id.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (id.getSingle(event) != null)
            return new String[]{CitizensAPI.getNPCRegistry().getById(id.getSingle(event)).getName()};
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        if (id != null) {
            net.citizensnpcs.api.npc.NPC npc = CitizensAPI.getNPCRegistry().getById(id.getSingle(event));
            if (mode == Changer.ChangeMode.SET) npc.setName((String) delta[0]);
            else if (mode == (Changer.ChangeMode.DELETE)) npc.setName("");
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE)
            return ch.njol.util.coll.CollectionUtils.array(String.class);
        return null;
    }
}