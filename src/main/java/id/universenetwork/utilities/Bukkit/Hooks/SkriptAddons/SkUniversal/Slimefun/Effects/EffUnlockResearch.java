package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Slimefun - Unlock Research")
@ch.njol.skript.doc.Description("Unlocks Slimefun research.")
@ch.njol.skript.doc.Examples({"unlock the slimefun research with id \"cool_research\" for player"})
public class EffUnlockResearch extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffUnlockResearch.class, "unlock [the] [Slimefun] research [with ID] %string% for %player%");
    }

    Expression<String> ID;
    Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        ID = (Expression<String>) e[0];
        player = (Expression<Player>) e[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "unlock Slimefun research with id " + ID.toString(e, b) + " for player " + player.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (ID.getSingle(e) == null || player.getSingle(e) == null) return;
        io.github.thebusybiscuit.slimefun4.api.researches.Research research = id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook.getResearch(ID.getSingle(e));
        if (research != null) research.unlock(player.getSingle(e), true);
    }
}