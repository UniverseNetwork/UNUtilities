package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SoundMuffler;

import com.comphenix.protocol.PacketType.Play.Server;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class SoundMufflerListener extends com.comphenix.protocol.events.PacketAdapter implements org.bukkit.event.Listener, io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    public SoundMufflerListener() {
        super(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, com.comphenix.protocol.events.ListenerPriority.NORMAL, Server.NAMED_SOUND_EFFECT, Server.ENTITY_SOUND);
        com.comphenix.protocol.ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    @Override
    public void onPacketSending(com.comphenix.protocol.events.PacketEvent event) {
        if (event.getPacketType() == Server.NAMED_SOUND_EFFECT || event.getPacketType() == Server.ENTITY_SOUND) {
            Location l;
            if (event.getPacketType() == Server.NAMED_SOUND_EFFECT) {
                int x = event.getPacket().getIntegers().read(0) >> 3;
                int y = event.getPacket().getIntegers().read(1) >> 3;
                int z = event.getPacket().getIntegers().read(2) >> 3;
                l = new Location(event.getPlayer().getWorld(), x, y, z);
            } else if (event.getPacketType() == Server.ENTITY_SOUND)
                l = event.getPlayer().getWorld().getEntities().stream().filter(e -> e.getEntityId() == event.getPacket().getIntegers().read(1)).map(org.bukkit.entity.Entity::getLocation).findAny().orElse(null);
            else return;
            if (l == null) return;
            Block soundMuff = findSoundMuffler(l);
            if (soundMuff != null && BlockStorage.getLocationInfo(soundMuff.getLocation(), "enabled") != null && BlockStorage.getLocationInfo(soundMuff.getLocation(), "enabled").equals("true") && getCharge(soundMuff.getLocation()) > 8) {
                int volume = Integer.parseInt(BlockStorage.getLocationInfo(soundMuff.getLocation(), "volume"));
                if (volume == 0) event.setCancelled(true);
                else
                    event.getPacket().getFloat().write(0, (float) volume / 100.0f);
            }
        }
    }

    Block findSoundMuffler(Location l) {
        final int dis = SoundMufflerMachine.DISTANCE;
        for (int x = l.getBlockX() - dis; x < l.getBlockX() + dis; x++)
            for (int y = l.getBlockY() - dis; y < l.getBlockY() + dis; y++)
                for (int z = l.getBlockZ() - dis; z < l.getBlockZ() + dis; z++) {
                    if (!l.getWorld().isChunkLoaded(x >> 4, z >> 4)) continue;
                    Block b = l.getWorld().getBlockAt(x, y, z);
                    if (b.getType() == org.bukkit.Material.WHITE_CONCRETE && BlockStorage.hasBlockInfo(b)) {
                        io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem item = BlockStorage.check(b);
                        if (item.getId().equals("SOUND_MUFFLER")) return b;
                    }
                }
        return null;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 352;
    }

    @Override
    public String getId() {
        return "SOUND_MUFFLER";
    }
}