package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.event.Event;

@Name("NPC Skin")
@Description({"Allows you to set the skin of the specified npc to the specified player's name (the string value).", "NOTE: This only works if the npc's type is a player!"})
@Examples("set skin of npc last spawned npc to \"hapily\"")
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffNPCSkin extends ch.njol.skript.lang.Effect {
    static {
        ch.njol.skript.Skript.registerEffect(EffNPCSkin.class, "set skin of npc %integer% to %string%");
    }

    Expression<Integer> id;
    Expression<String> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.id = (Expression<Integer>) expressions[0];
        this.player = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "NPC vulnerability effect with expression integer: " + id.toString(event, debug) + " and expression string: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        NPC npc = reg.getById(id.getSingle(event));
        npc.data().setPersistent(NPC.PLAYER_SKIN_UUID_METADATA, player.getSingle(event));
        Location npcloc = npc.getStoredLocation();
        npc.despawn();
        npc.spawn(npcloc);
    }
}