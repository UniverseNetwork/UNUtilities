package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

@ch.njol.skript.doc.Name("On NPC Click")
@ch.njol.skript.doc.Description({"Checks when a player clicks an npc."})
@ch.njol.skript.doc.Examples({"npc click:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCClick extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("NPC Click", SimpleEvent.class, NPCClickEvent.class, "npc click");
        registerEventValue(NPCClickEvent.class, Integer.class, new Getter<Integer, NPCClickEvent>() {
            @Override
            @Nullable
            public Integer get(NPCClickEvent e) {
                return e.getNPC().getId();
            }
        }, 0);
        registerEventValue(NPCClickEvent.class, Player.class, new Getter<Player, NPCClickEvent>() {
            @Override
            @Nullable
            public Player get(NPCClickEvent e) {
                return e.getClicker();
            }
        }, 0);
    }
}