package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Injector;

public class NMSReflection {
    static int protocolVersion = -1;
    static final int PROTOCOL_1_17 = 755;
    static String version;
    static java.lang.reflect.Field playerConnectionField;

    public static String getVersion() {
        return version == null ? version = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] : version;
    }

    public static int getProtocolVersion() {
        return protocolVersion == -1 ? protocolVersion = com.viaversion.viaversion.api.Via.getAPI().getServerVersion().lowestSupportedVersion() : protocolVersion;
    }

    public static Class<?> getBlockPositionClass() {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17) return Class.forName("net.minecraft.core.BlockPosition");
            return getLegacyNMSClass("BlockPosition");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getNMSBlock(String name) {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17) return Class.forName("net.minecraft.world.level.block." + name);
            return getLegacyNMSClass(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Class getSoundCategoryClass() {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17) return Class.forName("net.minecraft.sounds.SoundCategory");
            return getLegacyNMSClass("SoundCategory");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getPacketClass() {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17) return Class.forName("net.minecraft.network.protocol.Packet");
            return getLegacyNMSClass("Packet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getGamePacketClass(String packetType) {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17)
                return Class.forName("net.minecraft.network.protocol.game." + packetType);
            return getLegacyNMSClass(packetType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getPlayerConnectionClass() {
        try {
            if (getProtocolVersion() >= PROTOCOL_1_17)
                return Class.forName("net.minecraft.server.network.PlayerConnection");
            return getLegacyNMSClass("PlayerConnection");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getLegacyNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + getVersion() + "." + name);
    }

    public static Class<?> getCraftBukkitClass(String name) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + getVersion() + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendPacket(org.bukkit.entity.Player player, Object packet) {
        try {
            Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
            if (playerConnectionField == null)
                playerConnectionField = java.util.Arrays.stream(nmsPlayer.getClass().getFields()).filter(field -> field.getType() == getPlayerConnectionClass()).findFirst().orElseThrow(() -> new ReflectiveOperationException("Failed to find PlayerConnection field in EntityPlayer"));
            Object playerConnection = playerConnectionField.get(nmsPlayer);
            playerConnection.getClass().getMethod("sendPacket", getPacketClass()).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}