package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Slimefun - Add Research")
@ch.njol.skript.doc.Description("Adds Slimefun research to an item.")
@ch.njol.skript.doc.Examples({"add the slimefun research with ID \"cool_research\" to the slimefun item \"COOL_DIRT\""})
public class EffAddResearch extends ch.njol.skript.lang.Effect {
    static {
        Skript.registerEffect(EffAddResearch.class, "add [the] [Slimefun] research [with ID] %string% to [the] [Slimefun] [item] [with ID] %string%");
    }

    Expression<String> i;
    Expression<String> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(ch.njol.skript.events.bukkit.ScriptEvent.class)) {
            Skript.error("You can not use Slimefun add research expression in any event but on script load.");
            return false;
        }
        i = (Expression<String>) e[0];
        item = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "add Slimefun research " + i.toString(e, b) + " to item " + item.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (i.getSingle(e) == null || item.getSingle(e) == null) return;
        io.github.thebusybiscuit.slimefun4.api.researches.Research research = id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook.getResearch(i.getSingle(e));
        if (research != null) {
            research.addItems(io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem.getById(item.getSingle(e)));
            research.register();
        }
    }
}