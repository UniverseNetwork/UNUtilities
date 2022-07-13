package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Conditions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("AdvancedBan - Is Muted")
@ch.njol.skript.doc.Description("Checks if a player is muted.")
@ch.njol.skript.doc.Examples({"if the player is muted:"})
public class CondMuted extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondMuted.class, "%offlineplayer% is muted [by AdvancedBan]", "%offlineplayer% is(n't| not) muted [by AdvancedBan]");
    }

    Expression<OfflinePlayer> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event e, boolean b) {
        return "player" + player.toString(e, b) + " is muted";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return AdvancedBanHook.punishmentManager.isMuted(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName())) != isNegated();
    }
}