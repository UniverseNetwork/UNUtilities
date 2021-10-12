package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions.CondIsPrime.isPrime;

@Patterns("%number%(st|nd|rd|th) prime")
public class ExprNthPrime extends SimplePropertyExpression<Number, Integer> {
    @Override
    protected String getPropertyName() {
        return "prime";
    }

    @Override
    public Integer convert(Number n) {
        int candidate, count;
        for (candidate = 2, count = 0; count < n.intValue(); ++candidate) if (isPrime(candidate)) ++count;
        return candidate - 1;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}