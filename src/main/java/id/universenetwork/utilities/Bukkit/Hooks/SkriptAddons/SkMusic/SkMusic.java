package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic;

import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Effects.*;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Expressions.*;

import java.io.File;

import static ch.njol.skript.Skript.*;
import static ch.njol.skript.lang.ExpressionType.PROPERTY;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class SkMusic extends id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons {
    public static java.util.Map<org.bukkit.entity.Player, com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer> songPlayers = new java.util.HashMap();

    public SkMusic() {
        super("SkMusic");
    }

    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(new Events());
        registerEffect(PlayNBS.class, "[skmusic] [nbs] play [song][music] %string% to %player%");
        registerEffect(StopNBS.class, "[skmusic] [nbs] stop play[ing] [song][music] to %player%");
        registerEffect(BroadcastNBS.class, "[skmusic] [nbs] broadcast [song][music] %string%");
        registerEffect(StopNBSBroadcast.class, "[skmusic] [nbs] stop broadcast[ing] [song][music]");
        registerEffect(PlayNBSInRadius.class, "[skmusic] [nbs] play [song][music] %string% to [all] players in radius %integer% arround [location] %location%");
        registerEffect(SetVolume.class, "[skmusic] [nbs] set volume of [player] %player% to %integer%");
        registerEffect(SetFadeInDuration.class, "[skmusic] [nbs] set fade in duration of [player] %player% to %integer%");
        registerEffect(SetFadeOutDuration.class, "[skmusic] [nbs] set fade out duration of [player] %player% to %integer%");
        registerCondition(id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkMusic.Conditions.SongExist.class, "[skmusic] [nbs] (song|music) %string% exist");
        registerExpression(GetVolume.class, Integer.class, PROPERTY, "[skmusic] [nbs] volume of [player] %player%", "%player%['s] [music] volume");
        registerExpression(GetFadeInDuration.class, Integer.class, PROPERTY, "[skmusic] [nbs] fade in duration of [player] %player%");
        registerExpression(GetFadeOutDuration.class, Integer.class, PROPERTY, "[skmusic] [nbs] fade out duration of [player] %player%");
        registerExpression(GetSongTitle.class, String.class, PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) (name|title)");
        registerExpression(GetSongAuthor.class, String.class, PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) author");
        registerExpression(GetSongSpeed.class, Float.class, PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) speed");
        registerExpression(GetSongLength.class, Integer.class, PROPERTY, "[skmusic] [nbs] %player%['s] (song|music) (length|duration)");
        File dir = new File("plugins/Skript/musics");
        if (!dir.exists()) {
            System.out.println(prefix + " §fCreating SkMusic Directory...");
            dir.mkdirs();
        }
        System.out.println(prefix + " §bSuccessfully Registered §6SkMusic §bAddon");
    }
}