package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler;

import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

import static com.comphenix.protocol.PacketType.Play.Server.ENTITY_SOUND;
import static com.comphenix.protocol.PacketType.Play.Server.NAMED_SOUND_EFFECT;
import static com.comphenix.protocol.ProtocolLibrary.getProtocolManager;
import static com.comphenix.protocol.events.ListenerPriority.NORMAL;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler.SoundMufflerMachine.DISTANCE;
import static java.lang.Integer.parseInt;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;

public class SoundMufflerListener extends PacketAdapter implements Listener, EnergyNetComponent {
    public SoundMufflerListener() {
        super(UNUtilities.plugin, NORMAL, NAMED_SOUND_EFFECT, ENTITY_SOUND);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.getPacketType() == NAMED_SOUND_EFFECT || event.getPacketType() == ENTITY_SOUND) {
            Location l;
            if (event.getPacketType() == NAMED_SOUND_EFFECT) {
                int x = event.getPacket().getIntegers().read(0) >> 3;
                int y = event.getPacket().getIntegers().read(1) >> 3;
                int z = event.getPacket().getIntegers().read(2) >> 3;
                l = new Location(event.getPlayer().getWorld(), x, y, z);
            } else if (event.getPacketType() == ENTITY_SOUND) {
                l = event.getPlayer().getWorld().getEntities().stream().filter(e -> e.getEntityId() == event.getPacket().getIntegers().read(1)).map(Entity::getLocation).findAny().orElse(null);
            } else return;
            if (l == null) return;
            final Block soundMuff = findSoundMuffler(l);
            if (soundMuff != null && getLocationInfo(soundMuff.getLocation(), "enabled") != null && getLocationInfo(soundMuff.getLocation(), "enabled").equals("true") && getCharge(soundMuff.getLocation()) > 8) {
                int v = parseInt(getLocationInfo(soundMuff.getLocation(), "volume"));
                if (v == 0) event.setCancelled(true);
                else event.getPacket().getFloat().write(0, (float) v / 100.0f);
            }
        }
    }

    Block findSoundMuffler(Location l) {
        final int dis = DISTANCE;
        for (int x = l.getBlockX() - dis; x < l.getBlockX() + dis; x++)
            for (int y = l.getBlockY() - dis; y < l.getBlockY() + dis; y++)
                for (int z = l.getBlockZ() - dis; z < l.getBlockZ() + dis; z++) {
                    if (!l.getWorld().isChunkLoaded(x >> 4, z >> 4)) continue;
                    Block b = l.getWorld().getBlockAt(x, y, z);
                    if (b.getType() == Material.WHITE_CONCRETE && hasBlockInfo(b)) {
                        SlimefunItem item = check(b);
                        if (item.getId().equals("SOUND_MUFFLER")) return b;
                    }
                }
        return null;
    }

    public void start() {
        getProtocolManager().addPacketListener(this);
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return 352;
    }

    @NotNull
    @Override
    public String getId() {
        return "SOUND_MUFFLER";
    }
}