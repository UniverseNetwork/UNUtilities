package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.config.Config;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.SQL.ScriptCredentials;
import org.bukkit.event.Event;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

@Patterns("update %string%")
public class EffSQLUpdate extends Effect {
    File executor;
    Expression<String> query;
    String pool;

    @Override
    protected void execute(Event event) {
        String q = query.getSingle(event);
        if (q == null) return;
        Statement st = null;
        try {
            st = ScriptCredentials.get(executor, pool).getConnection(pool).createStatement();
            st.executeUpdate(q);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        return "sql query";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, ParseResult parseResult) {
        Config currentScript = ParserInstance.get().getCurrentScript();
        if (ScriptCredentials.get(currentScript.getFile()).getConnection() == null) {
            Skript.error("Database features are disabled until the script has SQL credentials associated with it.", ErrorQuality.SEMANTIC_ERROR);
            return false;
        }
        executor = currentScript.getFile();
        query = (Expression<String>) expressions[0];
        pool = ScriptCredentials.currentPool;
        ScriptCredentials.currentPool = "default";
        return true;
    }
}