package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Events;

import ch.njol.skript.Skript;
import com.vexsoftware.votifier.model.Vote;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook.GoGetter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

public class OnlinePlayerVoteEvent extends org.bukkit.event.Event {
    static final HandlerList HANDLERS = new HandlerList();
    public static final String[] PATTERNS = new String[]{"[online] [player] vote"};

    static {
        Skript.registerEvent("On Vote", ch.njol.skript.lang.util.SimpleEvent.class, OnlinePlayerVoteEvent.class, PATTERNS);
        registerEventValue(OnlinePlayerVoteEvent.class, Player.class, new GoGetter<>(OnlinePlayerVoteEvent::getPlayer), 0);
        registerEventValue(OnlinePlayerVoteEvent.class, Vote.class, new GoGetter<>(OnlinePlayerVoteEvent::getVote), 0);
    }

    final Player player;
    final Vote vote;

    public OnlinePlayerVoteEvent(Player player, Vote vote) {
        this.player = player;
        this.vote = vote;
    }

    public Player getPlayer() {
        return player;
    }

    public Vote getVote() {
        return vote;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}