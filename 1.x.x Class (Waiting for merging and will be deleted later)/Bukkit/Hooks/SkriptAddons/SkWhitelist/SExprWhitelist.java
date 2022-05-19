package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkWhitelist;

import ch.njol.skript.classes.Changer.ChangeMode;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static ch.njol.util.coll.CollectionUtils.array;

/**
 * Created by tim740 on 04/04/2016
 */
public class SExprWhitelist extends ch.njol.skript.lang.util.SimpleExpression<OfflinePlayer> {
    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        ArrayList<OfflinePlayer> cl = new ArrayList<>(Bukkit.getWhitelistedPlayers());
        return cl.toArray(new OfflinePlayer[0]);
    }

    @Override
    public void change(Event e, Object[] delta, ChangeMode mode) {
        if (mode == ChangeMode.ADD) ((OfflinePlayer) delta[0]).setWhitelisted(true);
        else if (mode == ChangeMode.REMOVE) ((OfflinePlayer) delta[0]).setWhitelisted(false);
        else if (mode == ChangeMode.RESET)
            for (OfflinePlayer sof : Bukkit.getWhitelistedPlayers()) sof.setWhitelisted(false);
        else if (mode == ChangeMode.SET) Bukkit.setWhitelist((Boolean) delta[0]);
    }

    @Override
    public boolean init(ch.njol.skript.lang.Expression<?>[] e, int i, ch.njol.util.Kleenean k, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final ChangeMode mode) {
        if (mode == ChangeMode.ADD || mode == ChangeMode.REMOVE) return array(OfflinePlayer.class);
        else if (mode == ChangeMode.RESET || mode == ChangeMode.SET) return array(Boolean.class);
        return null;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}