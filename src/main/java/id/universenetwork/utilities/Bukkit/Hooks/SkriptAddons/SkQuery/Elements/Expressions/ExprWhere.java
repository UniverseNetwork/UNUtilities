package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaCondition;
import org.bukkit.event.Event;

import java.util.ArrayList;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Collect.newArray;

@Patterns("%objects% where %predicate%")
public class ExprWhere extends SimpleExpression<Object> {
    public Expression<?> objects;
    public Expression<LambdaCondition> lambda;
    public ClassInfo<?> returnType = Classes.getExactClassInfo(Object.class);

    @Override
    protected Object[] get(Event e) {
        if (lambda == null) return null;
        ArrayList<Object> out = new ArrayList<>();
        for (Object o : objects.getAll(e)) {
            ExprInput.setInput(e, o);
            if (lambda.getSingle(e).check(e)) out.add(o);
            ExprInput.removeInput(e);
        }
        Object[] array = newArray(returnType.getC(), out.size());
        return out.toArray(array);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean isLoopOf(String s) {
        return returnType.getCodeName().equals(s);
    }

    @Override
    public Class<?> getReturnType() {
        return returnType.getC();
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "stream";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        objects = exprs[0];
        lambda = (Expression<LambdaCondition>) exprs[1];
        returnType = Classes.getExactClassInfo(objects.getReturnType());
        //if (objects instanceof Variable) {
        //    objects = objects.getConvertedExpression(Object.class);
        //}
        return true;
    }
}