package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Effects;

import ch.njol.skript.doc.*;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.lang.Expression;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

@Name("Spawn NPC")
@Description({"Creates an npc with the specified name and spawns it at the specified location as the specified type if it is set (default type is a player)."})
@Examples("spawn npc named \"hapily\" at player as player")
@Since("1.0")
@RequiredPlugins("Citizens")
public class EffSpawnNPC extends ch.njol.skript.lang.Effect {
    static final com.google.common.collect.BiMap<EntityData, EntityType> CACHE = com.google.common.collect.HashBiMap.create();

    static {
        for (org.bukkit.entity.EntityType e : org.bukkit.entity.EntityType.values()) {
            Class<? extends org.bukkit.entity.Entity> c = e.getEntityClass();
            if (c != null)
                CACHE.put(EntityData.fromClass(c), e); // Cache Skript EntityData -> Bukkit EntityType
        }
        ch.njol.skript.Skript.registerEffect(EffSpawnNPC.class, "(spawn|create) [a] npc named %string% at %location% as %entitydata%");
    }

    Expression<String> name;
    Expression<Location> loc;
    Expression<EntityData> type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, ch.njol.util.Kleenean isDelayed, ch.njol.skript.lang.SkriptParser.ParseResult parser) {
        this.name = (Expression<String>) expressions[0];
        this.loc = (Expression<Location>) expressions[1];
        this.type = (Expression<EntityData>) expressions[2];
        return true;
    }

    @Override
    public String toString(@org.jetbrains.annotations.Nullable Event event, boolean debug) {
        return "Spawn npc effect with expression string: " + name.toString(event, debug) + " and expression location: " + loc.toString(event, debug) + " and expression type: " + type.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        net.citizensnpcs.api.npc.NPCRegistry reg = net.citizensnpcs.api.CitizensAPI.getNPCRegistry();
        EntityType t = toBukkitEntityType(type.getSingle(event));
        net.citizensnpcs.api.npc.NPC npc = reg.createNPC(t, name.getSingle(event));
        npc.spawn(loc.getSingle(event));
        id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Elements.Expressions.ExprLastNPC.lastNPCCreated = npc;
    }

    static org.bukkit.entity.EntityType toBukkitEntityType(EntityData e) {
        return CACHE.get(EntityData.fromClass(e.getType())); // Fix Comparison Issues
    }

    static EntityData toSkriptEntityData(org.bukkit.entity.EntityType e) {
        return CACHE.inverse().get(e);
    }
}