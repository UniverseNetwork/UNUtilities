package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

@id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns("%number%(st|nd|rd|th) prime")
public class ExprNthPrime extends ch.njol.skript.expressions.base.SimplePropertyExpression<Number, Integer> {
    @Override
    protected String getPropertyName() {
        return "prime";
    }

    @Override
    public Integer convert(Number n) {
        int candidate, count;
        for (candidate = 2, count = 0; count < n.intValue(); ++candidate)
            if (id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Conditions.CondIsPrime.isPrime(candidate))
                ++count;
        return candidate - 1;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}