package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Skream.Elements.Events;

import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.util.Getter;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static ch.njol.skript.registrations.EventValues.registerEventValue;

@ch.njol.skript.doc.Name("On NPC RightClick")
@ch.njol.skript.doc.Description({"Checks when a player rightclicks an npc."})
@ch.njol.skript.doc.Examples({"npc rightclick:"})
@ch.njol.skript.doc.RequiredPlugins("Citizens")
public abstract class EvntNPCRightClick extends SimpleEvent {
    static {
        ch.njol.skript.Skript.registerEvent("PC Rightclick", SimpleEvent.class, NPCRightClickEvent.class, "npc rightclick");
        registerEventValue(NPCRightClickEvent.class, Integer.class, new Getter<>() {
            @Override
            @Nullable
            public Integer get(NPCRightClickEvent e) {
                return e.getNPC().getId();
            }

        }, 0);
        registerEventValue(NPCRightClickEvent.class, Player.class, new Getter<>() {
            @Override
            @Nullable
            public Player get(NPCRightClickEvent e) {
                return e.getClicker();
            }

        }, 0);
    }
}