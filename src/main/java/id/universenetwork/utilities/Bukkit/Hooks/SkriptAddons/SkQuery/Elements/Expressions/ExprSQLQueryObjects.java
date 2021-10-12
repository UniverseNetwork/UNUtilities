package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.config.Config;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials;
import org.bukkit.event.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static ch.njol.skript.log.ErrorQuality.SEMANTIC_ERROR;

@Patterns("objects in column %string% from %queryresult%")
public class ExprSQLQueryObjects extends SimpleExpression<Object> {
    Expression<ResultSet> query;
    Expression<String> column;

    @Override
    protected Object[] get(Event event) {
        try {
            ResultSet q = query.getSingle(event);
            String c = column.getSingle(event);
            if (q == null || c == null) return null;
            ArrayList<Object> output = new ArrayList<>();
            while (q.next()) output.add(q.getObject(c));
            return output.toArray(new Object[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "sql query";
    }

    @Override
    public boolean isLoopOf(String s) {
        return s.equals("object");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        Config currentScript = ParserInstance.get().getCurrentScript();
        if (ScriptCredentials.get(currentScript.getFile()).getConnection() == null) {
            Skript.error("Database features are disabled until the script has SQL credentials associated with it.", SEMANTIC_ERROR);
            return false;
        }
        query = (Expression<ResultSet>) expressions[1];
        column = (Expression<String>) expressions[0];
        return true;
    }
}