package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.Expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class GetSongSpeed extends SimpleExpression<Float> {
    Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Float> getReturnType() {
        return Float.class;
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[skmusic|nbs] %player%['s] (song|music) speed";
    }

    @Override
    @Nullable
    protected Float[] get(Event e) {
        Player p = player.getSingle(e);
        float speed = 0.0F;
        if (songPlayers.containsKey(p)) speed = songPlayers.get(p).getSong().getSpeed();
        return new Float[]{speed};
    }
}