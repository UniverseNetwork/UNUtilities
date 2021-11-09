package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("Plugin of Command")
@ch.njol.skript.doc.Description({"Returns the name of the plugin that created the specified command."})
@ch.njol.skript.doc.Examples("set {plugin} to plugin of command \"punish\"")
@ch.njol.skript.doc.Since("1.0")
public class ExprPluginCMD extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprPluginCMD.class, String.class, ch.njol.skript.lang.ExpressionType.COMBINED, "[the] plugin of [the] command %string%");
    }

    Expression<String> cmd;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        cmd = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Plugin of Command expression with expression string: " + cmd.toString(event, debug);
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (cmd != null) {
            org.bukkit.command.PluginCommand a = org.bukkit.Bukkit.getServer().getPluginCommand(cmd.getSingle(event));
            if (a == null) return null;
            else return new String[]{a.getPlugin().getName()};
        }
        return null;
    }
}