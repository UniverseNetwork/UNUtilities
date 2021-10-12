package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.Comparator;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.registrations.Comparators;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns({"%object%[ ]===[ ]%object%", "%object%[ ]==[ ]%object%", "%object%[ ]\\>[ ]%object%", "%object%[ ]\\<[ ]%object%", "%object%[ ]\\>=[ ]%object%", "%object%[ ]\\<=[ ]%object%"})
public class ExprComparisons extends SimpleExpression<Boolean> {
    Expression<?> first, second;
    int match;

    @Override
    protected Boolean[] get(Event e) {
        Comparator.Relation r = Comparators.compare(first.getSingle(e), second.getSingle(e));
        switch (match) {
            case 0:
                return asArray(Comparator.Relation.EQUAL.is(r));
            case 1:
                return asArray((first.getSingle(e) + "").equals(second.getSingle(e) + ""));
            case 2:
                return asArray(Comparator.Relation.GREATER.is(r));
            case 3:
                return asArray(Comparator.Relation.SMALLER.is(r));
            case 4:
                return asArray(Comparator.Relation.GREATER_OR_EQUAL.is(r));
            case 5:
                return asArray(Comparator.Relation.SMALLER_OR_EQUAL.is(r));
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "compare";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        first = exprs[0];
        second = exprs[1];
        match = matchedPattern;
        if (first instanceof Variable && second instanceof Variable) {
            first = first.getConvertedExpression(Object.class);
            second = second.getConvertedExpression(Object.class);
        } else if (first instanceof Literal<?> && second instanceof Literal<?>) {
            first = first.getConvertedExpression(Object.class);
            second = second.getConvertedExpression(Object.class);
            if (first == null || second == null) return false;
        } else {
            if (first instanceof Literal<?>) {
                first = first.getConvertedExpression(second.getReturnType());
                if (first == null) return false;
            } else if (second instanceof Literal<?>) {
                second = second.getConvertedExpression(first.getReturnType());
                if (second == null) return false;
            }
            if (first instanceof Variable) first = first.getConvertedExpression(second.getReturnType());
            else if (second instanceof Variable) second = second.getConvertedExpression(first.getReturnType());
            assert first != null && second != null;
        }
        return true;
    }
}