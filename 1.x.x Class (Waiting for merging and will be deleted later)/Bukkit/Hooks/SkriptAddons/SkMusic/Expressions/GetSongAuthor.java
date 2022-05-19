package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Expressions;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class GetSongAuthor extends ch.njol.skript.lang.util.SimpleExpression<String> {
    Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[skmusic|nbs] %player%['s] (song|music) author";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Player p = player.getSingle(e);
        String author = "none";
        if (songPlayers.containsKey(p)) author = songPlayers.get(p).getSong().getAuthor();
        return new String[]{author};
    }
}