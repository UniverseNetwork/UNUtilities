package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Util.Region;

import ch.njol.util.coll.iterator.CheckedIterator;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class CuboidIterator extends CheckedIterator<Block> {
    public CuboidIterator(Location pos1, Location pos2) {
        super(new CuboidRegion(pos1, pos2).iterator(), block -> new CuboidRegion(pos1, pos2).checkHas(block.getLocation().toVector()));
    }

    public CuboidIterator(Chunk chunk) {
        super(new CuboidRegion(chunk.getBlock(0, 0, 0).getLocation(), chunk.getBlock(15, chunk.getWorld().getMaxHeight() - 1, 15).getLocation()).iterator(), block -> new CuboidRegion(chunk.getBlock(0, 0, 0).getLocation(), chunk.getBlock(15, chunk.getWorld().getMaxHeight() - 1, 15).getLocation()).checkHas(block.getLocation().toVector()));
    }
}