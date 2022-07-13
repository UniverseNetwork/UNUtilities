package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Conditions;

import ch.njol.skript.lang.Expression;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("Has Advancement")
@ch.njol.skript.doc.Description({"Checks if a player an advancement."})
@ch.njol.skript.doc.Examples({"if player has advancement \"adventure/shoot_arrow\":"})
public class CondHasAdvancement extends ch.njol.skript.lang.Condition {
    static {
        ch.njol.skript.Skript.registerCondition(CondHasAdvancement.class, "%player% (1¦has|2¦does(n't| not) have) [the] advancement %string%");
    }

    Expression<Player> player;
    Expression<String> advancement;

    @SuppressWarnings({"unchecked", "null"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.advancement = (Expression<String>) expressions[1];
        setNegated(parser.mark == 1);
        return true;
    }

    @Override
    public @NonNull String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Player expression: " + player.toString(event, debug) + " String expression: " + advancement.toString(event, debug);
    }

    @Override
    public boolean check(@NonNull Event event) {
        Player p = player.getSingle(event);
        if (p == null) return isNegated();
        NamespacedKey key = NamespacedKey.minecraft(advancement.toString().replaceAll("[^a-zA-Z/:_]", ""));
        return p.getPlayer().getAdvancementProgress(org.bukkit.Bukkit.getAdvancement(key)).isDone() == isNegated();
    }
}