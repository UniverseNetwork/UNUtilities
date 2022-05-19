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

    Expression<Player> p;
    Expression<String> a;

    @SuppressWarnings({"unchecked"})
    @Override
    public boolean init(Expression<?>[] e, int m, ch.njol.util.Kleenean i, ch.njol.skript.lang.SkriptParser.ParseResult p) {
        this.p = (Expression<Player>) e[1];
        this.a = (Expression<String>) e[0];
        return true;
    }

    @Override
    public @NonNull String toString(@org.jetbrains.annotations.Nullable Event e, boolean d) {
        return "Add advancement effect: " + a.toString(e, d) + " and player expression: " + p.toString(e, d);
    }

    @Override
    protected void execute(@NonNull Event e) {
        if (p == null) return;
        for (Player u : p.getAll(e)) {
            NamespacedKey k = NamespacedKey.minecraft(a.toString().replaceAll("[^a-zA-Z/:_]", ""));
            org.bukkit.advancement.AdvancementProgress progress = u.getPlayer().getAdvancementProgress(org.bukkit.Bukkit.getAdvancement(k));
            for (String c : progress.getRemainingCriteria())
                progress.awardCriteria(c);
        }
    }
}