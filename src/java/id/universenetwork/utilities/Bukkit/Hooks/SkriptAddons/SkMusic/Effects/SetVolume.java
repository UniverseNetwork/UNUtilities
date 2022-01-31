package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class SetVolume extends ch.njol.skript.lang.Effect {
    Expression<Integer> vol;
    Expression<Player> player;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        vol = (Expression<Integer>) expr[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] set volume of [player] %player% to %integer%";
    }

    @Override
    public void execute(Event e) {
        Player p = player.getSingle(e);
        byte volume = vol.getSingle(e).byteValue();
        if (songPlayers.containsKey(p)) songPlayers.get(p).setVolume(volume);
    }
}