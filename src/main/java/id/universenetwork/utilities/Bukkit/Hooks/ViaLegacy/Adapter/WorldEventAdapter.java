package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Adapter;

import com.comphenix.protocol.events.PacketContainer;
import id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Utils.SplashTranslator;

public class WorldEventAdapter extends com.comphenix.protocol.events.PacketAdapter {
    public WorldEventAdapter() {
        super(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, com.comphenix.protocol.PacketType.Play.Server.WORLD_EVENT);
    }

    @Override
    public void onPacketSending(com.comphenix.protocol.events.PacketEvent event) {
        if (event.isPlayerTemporary()) return;
        final PacketContainer packet = event.getPacket();
        final int effectId = packet.getIntegers().read(0);
        if (effectId == 2002 || effectId == 2007) {
            final org.bukkit.entity.Player player = event.getPlayer();
            final int version = com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(player);
            final PacketContainer edited = packet.deepClone();
            final com.comphenix.protocol.reflect.StructureModifier<Integer> editedIntegers = edited.getIntegers();
            if (version <= 210) editedIntegers.write(0, 2002);
            for (final SplashTranslator translator : SplashTranslator.values())
                if (editedIntegers.read(1) == translator.getRGB())
                    for (final id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Utils.TranslationData data : translator.getDatas())
                        if (data.getLowestVersion() <= version && data.getHighestVersion() >= version)
                            editedIntegers.write(1, data.getRemap());
            try {
                com.comphenix.protocol.ProtocolLibrary.getProtocolManager().sendServerPacket(player, edited, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            event.setCancelled(true);
        }
    }
}