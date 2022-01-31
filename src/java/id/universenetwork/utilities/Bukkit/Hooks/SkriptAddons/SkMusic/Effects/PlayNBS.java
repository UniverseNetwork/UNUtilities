package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.io.File;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.SkMusic.songPlayers;

public class PlayNBS extends ch.njol.skript.lang.Effect {
    Expression<String> song;
    Expression<Player> player;

    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, ch.njol.util.Kleenean paramKleenean, ch.njol.skript.lang.SkriptParser.ParseResult paramParseResult) {
        song = (Expression<String>) expr[0];
        player = (Expression<Player>) expr[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "[skmusic|nbs] play [song|music] %string% to %player%";
    }

    @Override
    public void execute(Event e) {
        Player p = player.getSingle(e);
        String fileName = song.getSingle(e);
        if (!fileName.contains(".nbs")) fileName = fileName + ".nbs";
        File music = new File("plugins/Skript/musics", fileName);
        if (!music.exists())
            org.bukkit.Bukkit.getLogger().severe(id.universenetwork.utilities.Bukkit.UNUtilities.prefix + " §cError while loading §f" + song.getSingle(e) + "§c. Maybe the song does not exist?");
        else {
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