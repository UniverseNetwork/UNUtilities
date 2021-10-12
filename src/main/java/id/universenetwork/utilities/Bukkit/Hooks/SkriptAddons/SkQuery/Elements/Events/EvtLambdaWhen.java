package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Events;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAPIException;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript.LambdaCondition;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.HashSet;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getScheduler;

public class EvtLambdaWhen extends SkriptEvent {
    static {
        Skript.registerEvent("*Lambda when", EvtLambdaWhen.class, LambdaEvent.class, "when %predicate% [[with] limit[(ing|er)] %-boolean%]");
        getScheduler().scheduleSyncRepeatingTask(plugin, () -> Bukkit.getServer().getPluginManager().callEvent(new LambdaEvent()), 1, 1);
    }

    LambdaCondition lambda;
    Boolean limit;
    public static Set<LambdaCondition> limiter = new HashSet<>();

    @Override
    public boolean check(Event e) {
        if (limit != null && lambda.check(e) && limit) {
            if (!limiter.contains(lambda)) limiter.add(lambda);
            else return false;
        }
        return lambda.check(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
        lambda = ((Literal<LambdaCondition>) args[0]).getSingle();
        if (args.length > 1 && args[1] != null) limit = ((Literal<Boolean>) args[1]).getSingle();
        if (!(lambda instanceof LambdaCondition))
            throw new SkriptAPIException("Invalid use of LambdaWhen: argument must be a predicate!");
        return true;
    }

    @Override
    public String toString(Event arg0, boolean arg1) {
        return "Lambda when event";
    }

    static class LambdaEvent extends Event {
        final static HandlerList handlers = new HandlerList();

        public static HandlerList getHandlerList() {
            return handlers;
        }

        @Override
        public HandlerList getHandlers() {
            return handlers;
        }
    }
}