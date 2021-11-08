package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("On Velocity Change")
@ch.njol.skript.doc.Description({"Checks when a player's velocity changes."})
@ch.njol.skript.doc.Examples({"on velocity change:"})
public abstract class EvntVelocityChange extends SkriptEvent {
    static {
        ch.njol.skript.Skript.registerEvent("Velocity Change", SimpleEvent.class, PlayerVelocityEvent.class, "[player] velocity (change|shift)");
        EventValues.registerEventValue(PlayerVelocityEvent.class, Vector.class, new Getter<>() {
            @Override
            @Nullable
            public Vector get(PlayerVelocityEvent e) {
                return e.getVelocity();
            }
        }, 0);
        EventValues.registerEventValue(PlayerVelocityEvent.class, Player.class, new Getter<>() {
            @Override
            @Nullable
            public Player get(PlayerVelocityEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }
}