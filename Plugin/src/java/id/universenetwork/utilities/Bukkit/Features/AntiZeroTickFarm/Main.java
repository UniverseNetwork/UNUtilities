package id.universenetwork.utilities.Bukkit.Features.AntiZeroTickFarm;

import id.universenetwork.utilities.Bukkit.Templates.Feature;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import java.util.List;

public class Main extends Feature {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonOut(BlockPistonExtendEvent e) {
        if (cfgSection.getBoolean("enabled")) {
            breakPlantsBeside(e.getBlock(), e.getDirection());
            breakPlantsAbove(e.getBlocks());
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onPistonIn(BlockPistonRetractEvent e) {
        if (cfgSection.getBoolean("enabled")) breakPlantsAbove(e.getBlocks());
    }

    private void breakPlantsBeside(Block block, BlockFace direction) {
        for (Block b : getNearbyBlocks(block, direction))
            if (b.getType() == Material.CACTUS) b.breakNaturally();
    }

    private void breakPlantsAbove(List<Block> blockList) {
        for (Block block : blockList) {
            Block target = block.getRelative(BlockFace.UP);
            if (target.getBlockData() instanceof Ageable) target.breakNaturally();
        }
    }

    private List<Block> getNearbyBlocks(Block block, BlockFace direction) {
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