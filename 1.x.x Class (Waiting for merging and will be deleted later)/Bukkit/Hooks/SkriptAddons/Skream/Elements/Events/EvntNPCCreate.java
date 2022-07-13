package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import net.citizensnpcs.api.event.NPCCreateEvent;

@ch.njol.skript.doc.Name("On NPC Create")
@ch.njol.skript.doc.Description({"Checks when an npc object is created (not spawned)."})
@ch.njol.skript.doc.Examples({"npc create:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCCreate extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Create", SimpleEvent.class, NPCCreateEvent.class, "npc create");
        ch.njol.skript.registrations.EventValues.registerEventValue(NPCCreateEvent.class, Integer.class, new ch.njol.skript.util.Getter<Integer, NPCCreateEvent>() {
            @Override
            @org.jetbrains.annotations.Nullable
            public Integer get(NPCCreateEvent e) {
                return e.getNPC().getId();
            }
        }, 0);
    }
}