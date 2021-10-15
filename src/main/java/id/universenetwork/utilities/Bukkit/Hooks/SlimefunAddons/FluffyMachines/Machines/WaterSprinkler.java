package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.machines.accelerators.AbstractGrowthAccelerator;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class WaterSprinkler extends AbstractGrowthAccelerator {
    public final ItemSetting<Double> successChance = new ItemSetting<>(this, "success-chance", 0.5);
    public static final int ENERGY_CONSUMPTION = 2;
    public static final int CAPACITY = 128;
    static final int RADIUS = 2;
    static final int PROGRESS_SLOT = 4;
    static final CustomItemStack noWaterItem = new CustomItemStack(Material.BUCKET, "&cNo water found", "", "&cPlease place water under the sprinkler!");
    static final CustomItemStack waterFoundItem = new CustomItemStack(Material.WATER_BUCKET, "&bWater detected");
    final ItemSetting<Boolean> particles = new ItemSetting<>(this, "particles", true);

    public WaterSprinkler(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        createPreset(this, "&bWater Sprinkler", blockMenuPreset -> {
            for (int i = 0; i < 9; i++)
                blockMenuPreset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
            blockMenuPreset.addItem(PROGRESS_SLOT, noWaterItem);
        });
        addItemSetting(successChance, particles);
    }

    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    public int getRadius() {
        return RADIUS;
    }

    @Override
    public int[] getInputSlots() {
        return new int[0];
    }

    @Override
    public int[] getOutputSlots() {
        return new int[0];
    }

    @Override
    protected void tick(@NotNull Block b) {
        final BlockMenu inv = BlockStorage.getInventory(b);
        boolean open = inv.hasViewer();
        if (b.getRelative(BlockFace.DOWN).getType() == Material.WATER) {
            if (open) inv.replaceExistingItem(PROGRESS_SLOT, waterFoundItem);
        } else {
            if (open) inv.replaceExistingItem(PROGRESS_SLOT, noWaterItem);
            return;
        }

        if (getCharge(b.getLocation()) >= getEnergyConsumption())
            for (int x = -getRadius(); x <= getRadius(); x++)
                for (int z = -getRadius(); z <= getRadius(); z++) {
                    final Block block = b.getRelative(x, 0, z);
                    if (particles.getValue())
                        block.getWorld().spawnParticle(Particle.WATER_SPLASH, block.getLocation().add(0.5D, 0.5D, 0.5D), 4, 0.1F, 0.1F, 0.1F);
                    BlockData blockData = block.getBlockData();
                    if (blockData instanceof Ageable) {
                        grow(block);
                        removeCharge(b.getLocation(), getEnergyConsumption());
                    }
                }
    }

    void grow(@NotNull Block crop) {
        final double random = ThreadLocalRandom.current().nextDouble();
        if (successChance.getValue() >= random) {
            if (crop.getType() == Material.SUGAR_CANE) {
                for (int i = 1; i < 3; i++) {
                    final Block above = crop.getRelative(BlockFace.UP, i);
                    if (above.getType().isAir()) {
                        above.setType(Material.SUGAR_CANE);
                        break;
                    } else if (above.getType() != Material.SUGAR_CANE) return;
                }
            } else {
                final Ageable ageable = (Ageable) crop.getBlockData();
                if (ageable.getAge() < ageable.getMaximumAge()) {
                    ageable.setAge(ageable.getAge() + 1);
                    crop.setBlockData(ageable);
                    crop.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, crop.getLocation().add(0.5D, 0.5D, 0.5D), 4, 0.1F, 0.1F, 0.1F);
                }
            }
        }
    }
}