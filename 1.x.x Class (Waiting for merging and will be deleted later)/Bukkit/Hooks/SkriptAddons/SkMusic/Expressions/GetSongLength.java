package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class GetSongLength extends ch.njol.skript.lang.util.SimpleExpression<Integer> {
    Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[skmusic|nbs] %player%['s] (song|music) (length|duration)";
    }

    @Override
    @Nullable
    protected Integer[] get(Event e) {
        Player p = player.getSingle(e);
        int length = 0;
        if (songPlayers.containsKey(p)) length = songPlayers.get(p).getSong().getLength();
        return new Integer[]{length};
    }
}