package id.universenetwork.utilities.Bukkit.NMS.v1_16_R1.Tasks;

import id.universenetwork.utilities.Bukkit.NMS.v1_16_R2.Tasks.UntrackerTask;
import net.minecraft.server.v1_16_R1.ChunkProviderServer;
import net.minecraft.server.v1_16_R1.Entity;
import net.minecraft.server.v1_16_R1.WorldServer;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Enums.Features.EntityTrackerFixer.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.*;
import static id.universenetwork.utilities.Bukkit.NMS.v1_16_R1.EntityTick.EntityTickManager.getInstance;
import static id.universenetwork.utilities.Bukkit.NMS.v1_16_R1.NMSEntityTracker.trackEntities;
import static org.bukkit.Bukkit.getWorld;
import static org.bukkit.Bukkit.getWorlds;

public class CheckTask extends BukkitRunnable {
    @Override
    public void run() {
        if (UntrackerTask.isRunning()) return;
        if (ETFBoolean(ENABLEALLWORLDS)) for (World world : getWorlds()) checkWorld(world.getName());
        else for (String worldName : ETFStringList(WORLDS)) {
            if (getWorld(worldName) == null) continue;
            checkWorld(worldName);
        }
    }

    public void checkWorld(String worldName) {
        WorldServer ws = ((CraftWorld) getWorld(worldName)).getHandle();
        ChunkProviderServer cps = ws.getChunkProvider();
        Set<Entity> trackAgain = new HashSet<>();
        int d = ETFInt(TRACKRANGE);
        for (Player player : getWorld(worldName).getPlayers())
            for (org.bukkit.entity.Entity ent : player.getNearbyEntities(d, d, d)) {
                Entity nms = ((CraftEntity) ent).getHandle();
                if (cps.playerChunkMap.trackedEntities.containsKey(nms.getId()) || !nms.valid) continue;
                trackAgain.add(nms);
            }
        trackEntities(cps, trackAgain);
        if (ETFBoolean(DISABLETICKUNTRACKED)) getInstance().enableTicking(trackAgain);
    }
}