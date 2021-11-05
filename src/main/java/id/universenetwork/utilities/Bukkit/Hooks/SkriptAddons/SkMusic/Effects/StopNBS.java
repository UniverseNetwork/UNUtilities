package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class StopNBS extends ch.njol.skript.lang.Effect {
    Expression<Player> player;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] stop play[ing] (song|music) to %player%";
    }

    @Override
    protected void execute(Event e) {
        Player p = player.getSingle(e);
        if (songPlayers.containsKey(p)) songPlayers.get(p).setPlaying(false);
    }
}