package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.BungeeAddon.Skript;

import org.bukkit.event.Event;

public class ExprServers extends ch.njol.skript.lang.util.SimpleExpression<String> {
    static {
        ch.njol.skript.Skript.registerExpression(ExprServers.class, String.class, ch.njol.skript.lang.ExpressionType.SIMPLE, "(all [grabbed]|[last] grabbed) [bungee[ ]cord] servers");
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] exprs, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    protected String[] get(Event e) {
        return EffGrabServers.servers;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "last grabbed bungeecord servers";
    }
}