package id.universenetwork.utilities.Bukkit.Features.AntiZeroTickFarm;

import id.universenetwork.utilities.Bukkit.Templates.Feature;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.List;

public class Main extends Feature implements Listener {
    @Override
    public void Load() {
        id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListeners(this);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonOut(BlockPistonExtendEvent e) {
        if (UNUtilities.cfg.getBoolean(configPath + "enabled")) {
            breakPlantsBeside(e.getBlock(), e.getDirection());
            breakPlantsAbove(e.getBlocks());
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonIn(BlockPistonRetractEvent e) {
        if (UNUtilities.cfg.getBoolean(configPath + "enabled")) breakPlantsAbove(e.getBlocks());
    }

    void breakPlantsBeside(Block block, BlockFace direction) {
        for (Block b : getNearbyBlocks(block, direction))
            if (b.getType() == org.bukkit.Material.CACTUS) b.breakNaturally();
    }

    void breakPlantsAbove(List<Block> blockList) {
        for (Block block : blockList) {
            Block target = block.getRelative(BlockFace.UP);
            if (target.getBlockData() instanceof org.bukkit.block.data.Ageable) target.breakNaturally();
        }

    }

    List<Block> getNearbyBlocks(Block block, BlockFace direction) {
        List<Block> blocks = new java.util.ArrayList();
        blocks.add(block.getRelative(direction).getRelative(direction));
        switch (direction) {
            case NORTH:
                blocks.add(block.getRelative(BlockFace.NORTH_WEST));
                blocks.add(block.getRelative(BlockFace.NORTH_EAST));
                break;
            case WEST:
                blocks.add(block.getRelative(BlockFace.NORTH_WEST));
                blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
                break;
            case SOUTH:
                blocks.add(block.getRelative(BlockFace.SOUTH_EAST));
                blocks.add(block.getRelative(BlockFace.SOUTH_WEST));
                break;
            case EAST:
                blocks.add(block.getRelative(BlockFace.NORTH_EAST));
                blocks.add(block.getRelative(BlockFace.SOUTH_EAST));
        }
        return blocks;
    }
}