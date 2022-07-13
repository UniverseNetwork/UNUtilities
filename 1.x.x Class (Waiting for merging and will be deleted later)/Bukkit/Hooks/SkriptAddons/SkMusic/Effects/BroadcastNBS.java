package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import java.io.File;

import static id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class BroadcastNBS extends ch.njol.skript.lang.Effect {
    Expression<String> song;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        song = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] broadcast [song|music] %string%";
    }

    @Override
    public void execute(Event e) {
        String fileName = song.getSingle(e);
        if (!fileName.contains(".nbs")) fileName = fileName + ".nbs";
        File music = new File("plugins/Skript/musics", fileName);
        if (!music.exists())
            Bukkit.getLogger().severe(id.universenetwork.utilities.bukkit.UNUtilities.prefix + " §cError while loading §f" + song.getSingle(e) + "§c. Maybe the song does not exist?");
        else for (org.bukkit.entity.Player p : Bukkit.getOnlinePlayers()) {
            com.xxmicloxx.NoteBlockAPI.model.Song s = com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder.parse(music);
            com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer sp = new com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer(s);
            if (songPlayers.containsKey(p)) {
                songPlayers.get(p).destroy();
                songPlayers.replace(p, sp);
            } else songPlayers.put(p, sp);
            sp.setAutoDestroy(true);
            sp.addPlayer(p);
            sp.setPlaying(true);
        }
    }
}