package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("Is NPC")
@ch.njol.skript.doc.Description({"Checks if the specified entity is an NPC."})
@ch.njol.skript.doc.Examples({"if target entity is npc:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public class CondIsNPC extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondIsNPC.class, "%entity% (1¦is|2¦is(n't| not)) [a] npc");
    }

    Expression<Entity> entity;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.entity = (Expression<Entity>) expressions[0];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Entity is npc " + entity.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Entity e = entity.getSingle(event);
        if (e == null) return isNegated();
        return e.hasMetadata("NPC") == isNegated();
    }
}