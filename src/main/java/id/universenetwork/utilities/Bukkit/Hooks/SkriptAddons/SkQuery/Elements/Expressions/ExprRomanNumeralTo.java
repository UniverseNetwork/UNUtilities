package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyFrom;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.PropertyTo;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.UsePropertyPatterns;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.RomanNumerals.toRoman;

@UsePropertyPatterns
@PropertyFrom("numbers")
@PropertyTo("roman num(ber|eral)")
public class ExprRomanNumeralTo extends SimplePropertyExpression<Number, String> {
    @Override
    protected String getPropertyName() {
        return "roman numeral";
    }

    @Override
    public String convert(Number number) {
        return toRoman(number.intValue());
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}