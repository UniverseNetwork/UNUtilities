package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.Conditions;

import ch.njol.skript.lang.Expression;
import id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.AdvancedBan.AdvancedBanHook;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

@ch.njol.skript.doc.Name("AdvancedBan - Is Banned")
@ch.njol.skript.doc.Description("Checks if a player is banned.")
@ch.njol.skript.doc.Examples({"if the player is banned:"})
public class CondBanned extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondBanned.class, "%offlineplayer% is banned [by AdvancedBan]", "%offlineplayer% is(n't| not) banned [by AdvancedBan]");
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
        return "player " + player.toString(e, b) + " is banned";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return AdvancedBanHook.punishmentManager.isBanned(AdvancedBanHook.uuidManager.getUUID(player.getSingle(e).getName())) != isNegated();
    }
}