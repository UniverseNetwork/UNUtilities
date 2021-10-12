package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.conditions.base.PropertyCondition.PropertyType;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

@Name("Is Block")
@Description("Checks whether or not a certain itemtype is a placeable block.")
@Patterns({"%itemtype% is [a] block", "%itemtype% (isn't|is not) [a] block"})
public class CondIsBlock extends Condition {
    Expression<ItemType> itemtype;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
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
        return PropertyCondition.toString(this, PropertyType.BE, event, debug, itemtype, "block");
    }
}