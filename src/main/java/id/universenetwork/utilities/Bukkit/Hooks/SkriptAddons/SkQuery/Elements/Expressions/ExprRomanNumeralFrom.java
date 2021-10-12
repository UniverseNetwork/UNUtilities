package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.RomanNumerals.fromRoman;

@UsePropertyPatterns
@PropertyFrom("strings")
@PropertyTo("arabic num(ber|eral)")
public class ExprRomanNumeralFrom extends SimplePropertyExpression<String, Number> {
    @Override
    protected String getPropertyName() {
        return "roman numeral";
    }

    @Override
    public Number convert(String number) {
        return fromRoman(number);
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }
}