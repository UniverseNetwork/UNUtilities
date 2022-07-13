package id.universenetwork.utilities.bukkit.features.SlimefunAddons.SoundMuffler;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public class SoundMufflerListener extends PacketAdapter implements Listener, EnergyNetComponent {
    public SoundMufflerListener() {
        super(UNUtilities.plugin, ListenerPriority.NORMAL,
                PacketType.Play.Server.NAMED_SOUND_EFFECT, PacketType.Play.Server.ENTITY_SOUND
        );
        ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    @Override
    public void onPacketSending(PacketEvent p) {
        if (p.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT
                || p.getPacketType() == PacketType.Play.Server.ENTITY_SOUND
        ) {
            Location l;
            if (p.getPacketType() == PacketType.Play.Server.NAMED_SOUND_EFFECT) {
                int x = p.getPacket().getIntegers().read(0) >> 3;
                int y = p.getPacket().getIntegers().read(1) >> 3;
                int z = p.getPacket().getIntegers().read(2) >> 3;
                l = new Location(p.getPlayer().getWorld(), x, y, z);
            } else if (p.getPacketType() == PacketType.Play.Server.ENTITY_SOUND)
                l = p.getPlayer().getWorld().getEntities().stream()
                        .filter(e -> e.getEntityId() == p.getPacket().getIntegers().read(1))
                        .map(Entity::getLocation)
                        .findAny().orElse(null);
            else return;
            if (l == null)
                return;
            Block b = findSoundMuffler(l);
            if (b != null
                    && BlockStorage.getLocationInfo(b.getLocation(), "enabled") != null
                    && BlockStorage.getLocationInfo(b.getLocation(), "enabled").equals("true")
                    && getCharge(b.getLocation()) > 8
            ) {
                int volume = Integer.parseInt(BlockStorage.getLocationInfo(b.getLocation(), "volume"));
                if (volume == 0) p.setCancelled(true);
                else
                    p.getPacket().getFloat().write(0, (float) volume / 100.0f);
            }
        }
    }

    private Block findSoundMuffler(Location l) {
        final int d = SoundMufflerMachine.DISTANCE;
        for (int x = l.getBlockX() - d; x < l.getBlockX() + d; x++)
            for (int y = l.getBlockY() - d; y < l.getBlockY() + d; y++)
                for (int z = l.getBlockZ() - d; z < l.getBlockZ() + d; z++) {
                    if (!l.getWorld().isChunkLoaded(x >> 4, z >> 4))
                        continue;
                    Block b = l.getWorld().getBlockAt(x, y, z);
                    if (b.getType() == Material.WHITE_CONCRETE && BlockStorage.hasBlockInfo(b)) {
                        SlimefunItem item = BlockStorage.check(b);
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