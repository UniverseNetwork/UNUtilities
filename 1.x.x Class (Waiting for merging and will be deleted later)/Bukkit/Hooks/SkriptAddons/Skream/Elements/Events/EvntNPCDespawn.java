package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import net.citizensnpcs.api.event.NPCDespawnEvent;

@ch.njol.skript.doc.Name("On NPC Despawn")
@ch.njol.skript.doc.Description({"Checks when an npc despawns."})
@ch.njol.skript.doc.Examples({"npc despawn:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCDespawn extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Despawn", SimpleEvent.class, NPCDespawnEvent.class, "npc de[ |-]spawn");
        ch.njol.skript.registrations.EventValues.registerEventValue(NPCDespawnEvent.class, Integer.class, new ch.njol.skript.util.Getter<Integer, NPCDespawnEvent>() {
            @Override
            @org.jetbrains.annotations.Nullable
            public Integer get(NPCDespawnEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
    }
}