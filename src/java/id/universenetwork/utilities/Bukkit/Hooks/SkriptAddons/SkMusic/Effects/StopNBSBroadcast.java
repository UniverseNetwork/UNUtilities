package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class StopNBSBroadcast extends ch.njol.skript.lang.Effect {
    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] stop broadcast[ing] [song|music]";
    }

    @Override
    protected void execute(Event e) {
        for (Player p : org.bukkit.Bukkit.getOnlinePlayers())
            if (songPlayers.containsKey(p)) songPlayers.get(p).setPlaying(false);
    }
}