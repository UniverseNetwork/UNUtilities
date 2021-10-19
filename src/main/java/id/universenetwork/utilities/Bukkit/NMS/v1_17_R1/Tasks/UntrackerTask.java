package id.universenetwork.utilities.Bukkit.NMS.v1_17_R1.Tasks;

import net.minecraft.server.level.ChunkProviderServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.PlayerChunkMap.EntityTracker;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.boss.EntityComplexPart;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Enums.Features.EntityTrackerFixer.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;
import static id.universenetwork.utilities.Bukkit.NMS.v1_17_R1.EntityTick.EntityTickManager.getInstance;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static id.universenetwork.utilities.Bukkit.Utils.ReflectionUtils.getClassPrivateField;
import static net.minecraft.server.MinecraftServer.getServer;
import static org.bukkit.Bukkit.*;

public class UntrackerTask extends BukkitRunnable {
    static boolean running = false;
    static Field trackerField;

    static {
        try {
            trackerField = getClassPrivateField(EntityTracker.class, "c");
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"deprecation", "resource"})
    @Override
    public void run() {
        if (getServer().recentTps[0] > ETFDouble(TPSLIMIT)) return;
        running = true;
        if (ETFBoolean(ENABLEALLWORLDS))
            for (World world : getWorlds()) untrackProcess(world.getName());
        else for (String worldName : ETFStringList(WORLDS)) untrackProcess(worldName);
        running = false;
    }

    void untrackProcess(String worldName) {
        if (getWorld(worldName) == null) return;
        //Set<net.minecraft.server.v1_14_R2.Entity> toRemove = new HashSet<>();
        Set<Integer> toRemove = new HashSet<>();
        int removed = 0;
        WorldServer ws = ((CraftWorld) getWorld(worldName)).getHandle();
        ChunkProviderServer cps = ws.getChunkProvider();
        try {
            for (EntityTracker et : cps.a.G.values()) {
                net.minecraft.world.entity.Entity nmsEnt = (net.minecraft.world.entity.Entity) trackerField.get(et);
                if (nmsEnt instanceof EntityPlayer || nmsEnt instanceof EntityEnderDragon || nmsEnt instanceof EntityComplexPart)
                    continue;
                if (nmsEnt instanceof EntityArmorStand && nmsEnt.getBukkitEntity().getCustomName() != null) continue;
                boolean remove = false;
                if (et.f.size() == 0) remove = true;
                else if (et.f.size() == 1) {
                    for (ServerPlayerConnection ep : et.f) if (!ep.d().getBukkitEntity().isOnline()) remove = true;
                    if (!remove) continue;
                }
                if (remove) {
                    //System.out.println("untracked: " + nmsEnt.getBukkitEntity().getType().name());
                    toRemove.add(nmsEnt.getId());
                    removed++;
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        for (int id : toRemove) {
            cps.a.G.remove(id);
            if (ETFBoolean(DISABLETICKUNTRACKED))
                getInstance().disableTicking(ws.getEntities().a(id));
        }
        if (ETFBoolean(LOG))
            if (removed > 0) getLogger().info(prefix + " §fUntracked " + removed + " entities in " + worldName);
        //System.out.println("cache now contains " + UntrackedEntitiesCache.getInstance().getCache(worldName).size() + " entities");
    }

    public static boolean isRunning() {
        return running;
    }
}