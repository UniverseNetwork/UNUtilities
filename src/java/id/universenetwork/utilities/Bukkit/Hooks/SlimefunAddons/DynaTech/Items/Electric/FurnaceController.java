package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;

import java.util.List;

public class FurnaceController extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    static final int J_PER_BLOCK = 128;

    public FurnaceController(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockTick());
    }

    public BlockTicker onBlockTick() {
        return new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block arg0, SlimefunItem arg1, Config arg2) {
                FurnaceController.this.tick(arg0);
            }
        };
    }

    public void tick(Block b) {
        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.UP || face == BlockFace.DOWN) continue;
            Block relBlock = b.getRelative(face);
            if (getMachines().contains(relBlock.getType()) && getCharge(b.getLocation()) >= J_PER_BLOCK) {
                io.github.thebusybiscuit.slimefun4.libraries.paperlib.features.blockstatesnapshot.BlockStateSnapshotResult result = io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib.getBlockState(relBlock, false);
                org.bukkit.block.BlockState state = result.getState();
                if (state instanceof Furnace && ((Furnace) state).getCookTimeTotal() > 0) {
                    Furnace furnace = (Furnace) state;
                    removeCharge(b.getLocation(), J_PER_BLOCK);
                    furnace.setBurnTime((short) 1600);
                    if (result.isSnapshot()) state.update(true, true);
                }
            }
        }
    }

    List<Material> getMachines() {
        List<Material> machines = new java.util.ArrayList<>();
        machines.add(Material.FURNACE);
        machines.add(Material.BLAST_FURNACE);
        machines.add(Material.SMOKER);
        return machines;
    }

    @Override
    public int getCapacity() {
        return 2048;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }
}