package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

public class SongExist extends ch.njol.skript.lang.Condition {
    Expression<String> song;

    @Override
    public boolean init(Expression<?>[] expr, int i, ch.njol.util.Kleenean kl, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        song = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "(song|music) %string% exist";
    }

    @Override
    public boolean check(Event e) {
        String music = song.getSingle(e);
        if (!music.contains(".nbs")) music = music + ".nbs";
        return new java.io.File("plugins/Skript/musics", music).exists();
    }
}