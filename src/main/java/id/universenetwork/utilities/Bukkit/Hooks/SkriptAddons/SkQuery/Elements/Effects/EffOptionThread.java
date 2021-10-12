package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects;

import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.TriggerItem;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Description;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Annotations.Patterns;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Elements.Effects.Base.Pragma;
import id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.CancellableBukkitTask;
import org.bukkit.event.Event;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.*;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static org.bukkit.Bukkit.getScheduler;

@Name("Safely Execute Thread")
@Description("Makes the next line of code run on a different thread. The code following it will be delayed until the thread finishes.")
@Patterns("$ thread")
public class EffOptionThread extends Pragma {
    final ExecutorService staticThreadPool = Executors.newFixedThreadPool(10);
    static Method walkMethod;

    static {
        try {
            walkMethod = TriggerItem.class.getDeclaredMethod("walk", Event.class);
            walkMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected TriggerItem walk(final Event e) {
        final Future<TriggerItem> toWalk = walkNext(getNext(), e);
        final CancellableBukkitTask task = new CancellableBukkitTask() {
            @Override
            public void run() {
                if (toWalk.isDone()) {
                    try {
                        TriggerItem.walk(toWalk.get(), e);
                    } catch (ExecutionException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        if (getNext() != null) TriggerItem.walk(getNext().getNext(), e);
                    }
                    cancel();
                }
            }
        };
        task.setTaskId(getScheduler().scheduleSyncRepeatingTask(plugin, task, 0, 1));
        return null;
    }

    public Future<TriggerItem> walkNext(final TriggerItem next, final Event e) {
        return staticThreadPool.submit(new Callable<TriggerItem>() {
            @Override
            public TriggerItem call() throws Exception {
                walkMethod.invoke(next, e);
                return next.getNext();
            }
        });
    }

    @Override
    protected void register(File executingScript, ParseResult parseResult) {
    }
}