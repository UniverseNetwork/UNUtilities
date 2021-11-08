package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCLookCloseChangeTargetEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

@ch.njol.skript.doc.Name("On NPC Lookclose")
@ch.njol.skript.doc.Description({"Checks when an npc looks at a player with the lookclose trait.", "NOTE: This is also called when an NPC stops looking at a player and when the npc despawns (event-player will be none under these circumstances)!"})
@ch.njol.skript.doc.Examples({"npc spawn:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCLookClose extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Look Close", SimpleEvent.class, NPCLookCloseChangeTargetEvent.class, "npc lookclose");
        registerEventValue(NPCLookCloseChangeTargetEvent.class, Integer.class, new Getter<>() {
            @Override
            @Nullable
            public Integer get(NPCLookCloseChangeTargetEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
        registerEventValue(NPCLookCloseChangeTargetEvent.class, Player.class, new Getter<>() {
            @Override
            @Nullable
            public Player get(NPCLookCloseChangeTargetEvent e) {
                return e.getNewTarget();
            }
        }, 0);
    }
}