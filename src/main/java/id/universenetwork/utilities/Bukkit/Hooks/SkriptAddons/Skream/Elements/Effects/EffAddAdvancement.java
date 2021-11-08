package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.lang.Expression;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("Grant Advancement")
@ch.njol.skript.doc.Description({"Gives a player an advancement."})
@ch.njol.skript.doc.Examples({"grant \"adventure/shoot_arrow\" to advancements of player"})
public class EffAddAdvancement extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffAddAdvancement.class, "(add|grant) %string% to [the] advancements of %player%");
    }

    Expression<Player> player;
    Expression<String> advancement;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[1];
        this.advancement = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public @NonNull String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Add advancement effect: " + advancement.toString(event, debug) + " and player expression: " + player.toString(event, debug);
    }

    @Override
    protected void execute(@NonNull Event event) {
        if (player == null) return;
        for (Player user : player.getAll(event)) {
            NamespacedKey key = NamespacedKey.minecraft(advancement.toString().replaceAll("[^a-zA-Z/:_]", ""));
            org.bukkit.advancement.AdvancementProgress progress = user.getPlayer().getAdvancementProgress(org.bukkit.Bukkit.getAdvancement(key));
            for (String criteria : progress.getRemainingCriteria())
                progress.awardCriteria(criteria);
        }
    }
}