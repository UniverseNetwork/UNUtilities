package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.Conditions;

import ch.njol.skript.lang.Expression;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("Slimefun - Has Unlocked Research")
@ch.njol.skript.doc.Description("Checks if a player has a research unlocked.")
@ch.njol.skript.doc.Examples({"if the player has unlocked the research with id \"cool_research\":"})
public class CondHasResearch extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondHasResearch.class, "%offlineplayer% has [unlocked] [the] [Slimefun] research [with ID] %string%", "%offlineplayer% has(n't| not) [unlocked] [the] [Slimefun] research [with ID] %string%");
    }

    Expression<OfflinePlayer> player;
    Expression<String> i;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        i = (Expression<String>) e[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " has research " + i.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null || i.getSingle(e) == null) return isNegated();
        java.util.Optional<PlayerProfile> profile = PlayerProfile.find(player.getSingle(e));
        return profile.map(playerProfile -> playerProfile.hasUnlocked(id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun.SlimefunHook.getResearch(i.getSingle(e))) != isNegated()).orElseGet(this::isNegated);
    }
}