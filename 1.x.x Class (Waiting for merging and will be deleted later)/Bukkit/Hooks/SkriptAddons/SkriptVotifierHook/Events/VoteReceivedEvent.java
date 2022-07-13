package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook.Events;

import com.vexsoftware.votifier.model.VotifierEvent;

public class VoteReceivedEvent {
    public static final String[] PATTERNS = new String[]{"[(any|every|raw)] [votifier] vote receiv(e|ed)", "[(any|every|raw)] votifier vote"};

    static {
        ch.njol.skript.Skript.registerEvent("On Votifier Vote", ch.njol.skript.lang.util.SimpleEvent.class, VotifierEvent.class, PATTERNS);
        ch.njol.skript.registrations.EventValues.registerEventValue(VotifierEvent.class, com.vexsoftware.votifier.model.Vote.class, new id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkriptVotifierHook.GoGetter<>(VotifierEvent::getVote), 0);
    }
}