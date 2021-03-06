package id.universenetwork.utilities.bukkit.NMS.v1_16_R1.EntityTick;

import net.minecraft.server.v1_16_R1.Entity;
import net.minecraft.server.v1_16_R1.EntityInsentient;
import net.minecraft.server.v1_16_R1.MinecraftServer;
import org.bukkit.craftbukkit.v1_16_R1.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Set;

import static id.universenetwork.utilities.bukkit.libraries.InfinityLib.Common.Events.registerListeners;

public class EntityTickManager implements org.bukkit.event.Listener {
    static EntityTickManager instance;

    EntityTickManager() {
        registerListeners(this);
    }

    public void disableTicking(Entity entity) {
        if (entity == null) return;
        if (!entity.valid) return;
        entity.activatedTick = -2147483648L;
        //System.out.println("disable tick for insentient entity currently aware is = " + ((EntityInsentient)entity).aware + " should be true");
        if (entity instanceof EntityInsentient) ((EntityInsentient) entity).aware = false;
    }

    public void enableTicking(Set<Entity> entities) {
        for (Entity entity : entities) {
            if (entity == null) continue;
            if (!entity.valid) continue;
            entity.activatedTick = MinecraftServer.currentTick;
            //System.out.println("enabling tick for insentient entity currently aware is = " + ((EntityInsentient)entity).aware + " should be false");
            if (entity instanceof EntityInsentient) ((EntityInsentient) entity).aware = true;
        }
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        for (org.bukkit.entity.Entity entity : event.getChunk().getEntities()) {
            Entity nms = ((CraftEntity) entity).getHandle();
            if (nms instanceof EntityInsentient)
                if (!((EntityInsentient) nms).aware) ((EntityInsentient) nms).aware = true;
        }
    }

    public static EntityTickManager getInstance() {
        if (instance == null) instance = new EntityTickManager();
        return instance;
    }
}