package id.universenetwork.utilities.Bukkit.NMS.v1_17_R1;

import net.minecraft.server.level.ChunkProviderServer;
import net.minecraft.server.level.PlayerChunkMap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static id.universenetwork.utilities.Bukkit.Utils.ReflectionUtils.getPrivateMethod;

public final class NMSEntityTracker {
    static Method addEntityMethod;
    static Method removeEntityMethod;

    static {
        try {
            addEntityMethod = getPrivateMethod(PlayerChunkMap.class, "addEntity", new Class[]{net.minecraft.world.entity.Entity.class});
            removeEntityMethod = getPrivateMethod(PlayerChunkMap.class, "removeEntity", new Class[]{net.minecraft.world.entity.Entity.class});
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    NMSEntityTracker() {
    }

    public static void trackEntities(ChunkProviderServer cps, Set<net.minecraft.world.entity.Entity> trackList) {
        try {
            for (net.minecraft.world.entity.Entity entity : trackList) addEntityMethod.invoke(cps.a, entity);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void untrackEntities(ChunkProviderServer cps, Set<net.minecraft.world.entity.Entity> untrackList) {
        try {
            for (net.minecraft.world.entity.Entity entity : untrackList) removeEntityMethod.invoke(cps.a, entity);
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}