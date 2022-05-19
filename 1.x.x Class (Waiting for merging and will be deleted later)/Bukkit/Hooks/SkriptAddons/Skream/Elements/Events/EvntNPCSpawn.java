package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

@ch.njol.skript.doc.Name("On NPC Spawn")
@ch.njol.skript.doc.Description({"Checks when an npc spawns.", "NOTE: This includes respawning."})
@ch.njol.skript.doc.Examples({"npc spawn:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCSpawn extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Spawn", SimpleEvent.class, NPCSpawnEvent.class, "npc spawn");
        registerEventValue(NPCSpawnEvent.class, Integer.class, new Getter<Integer, NPCSpawnEvent>() {
            @Override
            @Nullable
            public Integer get(NPCSpawnEvent e) {
                return e.getNPC().getId();
            }
        }, 0);
        registerEventValue(NPCSpawnEvent.class, Location.class, new Getter<Location, NPCSpawnEvent>() {
            @Override
            @Nullable
            public Location get(NPCSpawnEvent e) {
                return e.getLocation();
            }
        }, 0);
    }
}