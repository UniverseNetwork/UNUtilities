package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

@ch.njol.skript.doc.Name("On NPC LeftClick")
@ch.njol.skript.doc.Description({"Checks when a player leftclicks an npc."})
@ch.njol.skript.doc.Examples({"npc leftclick:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCLeftClick extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Leftclick", SimpleEvent.class, NPCLeftClickEvent.class, "npc leftclick");
        EventValues.registerEventValue(NPCLeftClickEvent.class, Integer.class, new Getter<Integer, NPCLeftClickEvent>() {
            @Override
            @Nullable
            public Integer get(NPCLeftClickEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
        EventValues.registerEventValue(NPCLeftClickEvent.class, Player.class, new Getter<Player, NPCLeftClickEvent>() {
            @Override
            @Nullable
            public Player get(NPCLeftClickEvent e) {
                return e.getClicker();
            }

        }, 0);
    }
}