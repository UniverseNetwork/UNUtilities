package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.Markup;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.asArray;

@Patterns("random %number% (char|long|length) string from [charset] %markup%")
public class ExprKeyString extends SimpleExpression<String> {
    Expression<Number> length;
    Expression<Markup> charset;

    @Override
    protected String[] get(Event event) {
        Number l = length.getSingle(event);
        Markup mk = charset.getSingle(event);
        if (l == null || mk == null) return null;
        int amt = l.intValue();
        String chars = mk.toString();
        return asArray(getKey(amt, chars));
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "random";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        length = (Expression<Number>) expressions[0];
        charset = (Expression<Markup>) expressions[1];
        return true;
    }

    public static String getKey(int length, String charset) {
        ArrayList<ArrayList<Integer>> charranges = new ArrayList<>();
        Pattern regex = Pattern.compile("(.)-(.)");
        Matcher m = regex.matcher(charset);
        while (m.find()) {
            ArrayList<Integer> range = new ArrayList<>();
            int first = m.group(1).charAt(0);
            int second = m.group(2).charAt(0);
            range.add(Math.min(first, second));
            range.add(Math.max(first, second));
            charranges.add(range);
        }
        Random rng = new Random();
        StringBuilder out = new StringBuilder();
        while (0 < length--) {
            ArrayList<Integer> current = charranges.get(rng.nextInt(charranges.size()));
            out.append((char) (rng.nextInt((current.get(1) - current.get(0)) + 1) + current.get(0)));
        }
        return out.toString();
    }
}