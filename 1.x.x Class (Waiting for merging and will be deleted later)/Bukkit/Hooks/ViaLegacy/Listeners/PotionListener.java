package id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Listeners;

import com.comphenix.protocol.events.PacketContainer;
import id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Utils.PotionTranslator;
import org.bukkit.entity.ThrownPotion;

public class PotionListener extends com.comphenix.protocol.events.PacketAdapter {
    public PotionListener() {
        super(id.universenetwork.utilities.bukkit.UNUtilities.plugin, com.comphenix.protocol.PacketType.Play.Server.SPAWN_ENTITY);
    }

    @Override
    public void onPacketSending(final com.comphenix.protocol.events.PacketEvent event) {
        final PacketContainer p = event.getPacket();
        final org.bukkit.entity.Entity entity = p.getEntityModifier(event).read(0);
        if (p.getIntegers().read(6) == 73 && entity instanceof ThrownPotion) {
            final org.bukkit.entity.Player player = event.getPlayer();
            final int v = com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(player);
            final PacketContainer edit = p.deepClone();
            for (final org.bukkit.potion.PotionEffect eff : ((ThrownPotion) entity).getEffects())
                for (final PotionTranslator translator : PotionTranslator.values())
                    if (eff.getType().equals(translator.getPotionEffectType()))
                        for (id.universenetwork.utilities.bukkit.Hooks.ViaLegacy.Utils.TranslationData data : translator.getDatas())
                            if (data.getLowestVersion() <= v && data.getHighestVersion() >= v)
                                edit.getIntegers().write(7, data.getRemap());
            try {
                com.comphenix.protocol.ProtocolLibrary.getProtocolManager().sendServerPacket(player, edit, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            event.setCancelled(true);
        }
    }
}