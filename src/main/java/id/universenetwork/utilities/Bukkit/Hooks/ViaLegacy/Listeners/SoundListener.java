package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Method;

import static com.viaversion.viaversion.api.Via.getAPI;
import static id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Injector.NMSReflection.*;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners;
import static org.bukkit.event.EventPriority.MONITOR;

public class SoundListener implements Listener {
    static boolean isSoundCategory = false;

    static {
        try {
            Class.forName("org.bukkit.SoundCategory");
            isSoundCategory = true;
        } catch (ClassNotFoundException ignore) {
        }
    }

    public SoundListener() {
        try {
            Class.forName("org.bukkit.event.entity.EntityPickupItemEvent");
            registerListeners(new Listener() {
                @EventHandler(priority = MONITOR, ignoreCancelled = true)
                public void onItemPickUp(org.bukkit.event.entity.EntityPickupItemEvent e) {
                    if (!(e.getEntity() instanceof Player)) return;
                    SoundListener.this.onItemPickUp((Player) e.getEntity());
                }
            });
        } catch (Exception e) {
            registerListeners(new Listener() {
                @EventHandler(priority = MONITOR, ignoreCancelled = true)
                public void onItemPickUp(org.bukkit.event.player.PlayerPickupItemEvent e) {
                    SoundListener.this.onItemPickUp(e.getPlayer());
                }
            });
        }
    }

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (getAPI().getPlayerVersion(player) > 47) return;
        playBlockPlaceSound(player, e.getBlock());
    }

    void onItemPickUp(Player player) {
        float volume = 0.2f;
        float pitch = (float) ((Math.random() - Math.random()) * 0.7f + 1.0f) * 2.0f;
        Location loc = player.getLocation();
        playSound(loc, Sound.ENTITY_ITEM_PICKUP, "PLAYERS", volume, pitch, 16, 47);
    }

    @EventHandler(priority = MONITOR, ignoreCancelled = true)
    void onExperienceOrbPickup(org.bukkit.event.player.PlayerExpChangeEvent e) {
        float volume = 0.1f;
        float pitch = (float) (0.5f * ((Math.random() - Math.random()) * 0.7f + 1.8f));
        playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, "PLAYERS", volume, pitch, 16, 47);
    }

    static void playSound(Location loc, Sound sound, String category, float volume, float pitch, double dist, int version) {
        Bukkit.getOnlinePlayers().stream().filter(p -> p.getWorld() == loc.getWorld()).filter(p -> p.getLocation().distanceSquared(loc) < dist * dist).filter(p -> getAPI().getPlayerVersion(p) <= version)
                .forEach(p -> {
                    if (isSoundCategory) p.playSound(loc, sound, SoundCategory.valueOf(category), volume, pitch);
                    else p.playSound(loc, sound, volume, pitch);
                });
    }

    static void playBlockPlaceSound(Player player, org.bukkit.block.Block block) {
        try {
            World world = block.getWorld();
            Object nmsWorld = world.getClass().getMethod("getHandle").invoke(world);
            Class<?> blockPositionClass = getBlockPositionClass();
            Object blockPosition = null;
            if (blockPositionClass != null)
                blockPosition = blockPositionClass.getConstructor(int.class, int.class, int.class).newInstance(block.getX(), block.getY(), block.getZ());
            Object blockData = nmsWorld.getClass().getSuperclass().getMethod("getType", blockPositionClass).invoke(nmsWorld, blockPosition);
            Method getBlock = blockData.getClass().getMethod("getBlock");
            getBlock.setAccessible(true);
            Object nmsBlock = getBlock.invoke(blockData);
            Method getStepSound;
            try {
                getStepSound = nmsBlock.getClass().getMethod("getStepSound", blockData.getClass());
            } catch (NoSuchMethodException e) {
                try {
                    getStepSound = nmsBlock.getClass().getMethod("getStepSound");
                } catch (NoSuchMethodException ex) {
                    getStepSound = nmsBlock.getClass().getMethod("w");
                }
            }
            Object soundType;
            if (getStepSound.getParameterCount() == 0) soundType = getStepSound.invoke(nmsBlock);
            else soundType = getStepSound.invoke(nmsBlock, blockData);
            Method soundEffectMethod;
            Method volumeMethod;
            Method pitchMethod;
            try {
                soundEffectMethod = soundType.getClass().getMethod("getPlaceSound");
                volumeMethod = soundType.getClass().getMethod("getVolume");
                pitchMethod = soundType.getClass().getMethod("getPitch");
            } catch (NoSuchMethodException e) {
                soundEffectMethod = soundType.getClass().getMethod("e");
                volumeMethod = soundType.getClass().getMethod("a");
                pitchMethod = soundType.getClass().getMethod("b");
            }
            Object soundEffect = soundEffectMethod.invoke(soundType);
            float volume = (float) volumeMethod.invoke(soundType);
            float pitch = (float) pitchMethod.invoke(soundType);
            Object soundCategory = Enum.valueOf(getSoundCategoryClass(), "BLOCKS");
            volume = (volume + 1.0f) / 2.0f;
            pitch *= 0.8;
            playSound(player, soundEffect, soundCategory, block.getX() + 0.5, block.getY() + 0.5, block.getZ() + 0.5, volume, pitch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void playSound(Player player, Object soundEffect, Object soundCategory, double x, double y, double z, float volume, float pitch) {
        try {
            Object packet = getGamePacketClass("PacketPlayOutNamedSoundEffect").getConstructor(soundEffect.getClass(), soundCategory.getClass(), double.class, double.class, double.class, float.class, float.class).newInstance(soundEffect, soundCategory, x, y, z, volume, pitch);
            sendPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}