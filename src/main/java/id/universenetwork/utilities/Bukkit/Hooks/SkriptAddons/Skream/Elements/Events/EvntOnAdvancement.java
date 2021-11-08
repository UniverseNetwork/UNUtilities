package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

@SuppressWarnings("null")
@ch.njol.skript.doc.Name("On Advancement Complete")
@ch.njol.skript.doc.Description({"Checks when a player gets an advancement."})
@ch.njol.skript.doc.Examples({"on advancement complete:"})
public abstract class EvntOnAdvancement extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("Advancement", SimpleEvent.class, PlayerAdvancementDoneEvent.class, "[player] advancement (complete|done)");
        ch.njol.skript.registrations.EventValues.registerEventValue(PlayerAdvancementDoneEvent.class, NamespacedKey.class, new Getter<>() {
            @Override
            @org.jetbrains.annotations.Nullable
            public NamespacedKey get(PlayerAdvancementDoneEvent e) {
                return NamespacedKey.minecraft(e.getAdvancement().getKey().getKey());
            }
        }, 0);
    }
}