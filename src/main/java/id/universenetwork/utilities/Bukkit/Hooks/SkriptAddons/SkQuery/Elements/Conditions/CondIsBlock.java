package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Is Block")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description("Checks whether or not a certain itemtype is a placeable block.")
@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns({"%itemtype% is [a] block", "%itemtype% (isn't|is not) [a] block"})
public class CondIsBlock extends ch.njol.skript.lang.Condition {
    Expression<ItemType> itemtype;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        itemtype = (Expression<ItemType>) expressions[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public boolean check(Event event) {
        return isNegated() != itemtype.getSingle(event).hasBlock();
    }

    @Override
    public String toString(Event event, boolean debug) {
        return PropertyCondition.toString(this, PropertyCondition.PropertyType.BE, event, debug, itemtype, "block");
    }
}