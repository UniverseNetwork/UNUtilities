package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.SkUniversal.Slimefun;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.entity.Player;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getRegistry;

public class SlimefunHook implements io.github.thebusybiscuit.slimefun4.api.SlimefunAddon {
    public final static SlimefunHook ADDON = new SlimefunHook();

    static {
        ch.njol.skript.Skript.registerEvent("Slimefun - Research Unlock", ch.njol.skript.lang.util.SimpleEvent.class, ResearchUnlockEvent.class, "[Slimefun] research unlock[ing]", "[Slimefun] unlock[ing] research").description("Called when a player unlocks a Slimefun research.").examples("on research unlock:", "\tsend \"You unlocked research!\"");
        EventValues.registerEventValue(ResearchUnlockEvent.class, Player.class, new Getter<Player, ResearchUnlockEvent>() {
            @Override
            public Player get(ResearchUnlockEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(ResearchUnlockEvent.class, String.class, new Getter<String, ResearchUnlockEvent>() {
            @Override
            public String get(ResearchUnlockEvent e) {
                return e.getResearch().getKey().getKey();
            }
        }, 0);
    }

    public static ItemGroup getItemGroup(String id) {
        for (ItemGroup category : getRegistry().getAllItemGroups())
            if (category.getKey().getKey().equalsIgnoreCase(id)) return category;
        return null;
    }

    public static Research getResearch(String id) {
        for (Research research : getRegistry().getResearches())
            if (research.getKey().getKey().equalsIgnoreCase(id)) return research;
        return null;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public org.bukkit.plugin.java.JavaPlugin getJavaPlugin() {
        return ch.njol.skript.Skript.getInstance();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/UniverseNetwork/UNUtilities/issues";
    }
}