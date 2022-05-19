package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static com.xxmicloxx.NoteBlockAPI.model.FadeType.LINEAR;
import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class SetFadeOutDuration extends ch.njol.skript.lang.Effect {
    Expression<Integer> dur;
    Expression<Player> player;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        player = (Expression<Player>) expr[0];
        dur = (Expression<Integer>) expr[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] set fade out duration of [player] %player% to %integer%";
    }

    @Override
    public void execute(Event e) {
        Player p = player.getSingle(e);
        int duration = dur.getSingle(e);
        if (songPlayers.containsKey(p)) {
            com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer song = songPlayers.get(p);
            song.getFadeOut().setType(LINEAR).setFadeDuration(duration);
        }
    }
}