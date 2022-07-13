package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Slimefun - Create Research")
@ch.njol.skript.doc.Description("Creates Slimefun research.")
@ch.njol.skript.doc.Examples({"create new slimefun research with ID \"cool_research\" and 2048 with name \"Cool Dirt Research\" with level 25"})
public class EffCreateResearch extends ch.njol.skript.lang.Effect {
    static {
        Skript.registerEffect(EffCreateResearch.class, "create [a] [new] [Slimefun] research [with ID] %string% and %integer% (named|with name) %string% with (cost|level) %integer%");
    }

    Expression<String> stringID;
    Expression<Integer> numID;
    Expression<String> name;
    Expression<Integer> level;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        if (!ch.njol.skript.ScriptLoader.isCurrentEvent(ch.njol.skript.events.bukkit.SkriptStartEvent.class)) {
            Skript.error("You can not use Slimefun create research effect in any event but on skript load.");
            return false;
        }
        stringID = (Expression<String>) e[0];
        numID = (Expression<Integer>) e[1];
        name = (Expression<String>) e[2];
        level = (Expression<Integer>) e[3];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "create Slimefun research with ID " + stringID.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (stringID.getSingle(e) == null || numID.getSingle(e) == null || name.getSingle(e) == null || level.getSingle(e) == null)
            return;
        new io.github.thebusybiscuit.slimefun4.api.researches.Research(new org.bukkit.NamespacedKey(Skript.getInstance(), stringID.getSingle(e)), numID.getSingle(e), name.getSingle(e), level.getSingle(e)).register();
    }
}