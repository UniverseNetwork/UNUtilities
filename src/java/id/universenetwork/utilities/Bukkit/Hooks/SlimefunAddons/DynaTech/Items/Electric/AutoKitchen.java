package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Autocrafter of Kitchen related recipes. Took heavy reference from FluffyMachines Autocrafters.
 *
 * @author ProfElements https://github.com/ProfElements
 * @author NCBPFluffyBear https://github.com/NCBPFluffyBear
 */
public class AutoKitchen extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine implements io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable {
    public static final int[] BORDER = {4, 5, 6, 7, 8, 13, 31, 40, 41, 42, 43, 44};
    static final MultiBlockMachine mblock = (MultiBlockMachine) id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.ExoticGardenRecipeTypes.KITCHEN.getMachine();
    static final int[] BORDER_IN = {0, 1, 2, 3, 12, 21, 30, 36, 37, 38, 39};
    static final int[] BORDER_OUT = {14, 15, 16, 17, 23, 26, 32, 33, 34, 35};

    public AutoKitchen(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) return;
        craftIfValid(b);
    }

    void craftIfValid(Block block) {
        BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(block);
        for (int outSlot : getOutputSlots()) {
            ItemStack outItem = menu.getItemInSlot(outSlot);
            if (outItem == null || outItem.getAmount() < outItem.getMaxStackSize()) break;
            else if (outSlot == getOutputSlots()[1]) return;
        }
        for (ItemStack[] input : RecipeType.getRecipeInputList(mblock)) {
            if (isCraftable(menu, input)) {
                ItemStack output = RecipeType.getRecipeOutputList(mblock, input).clone();
                craft(output, menu);
                removeCharge(block.getLocation(), getEnergyConsumption());
                return;
            }
        }
    }

    boolean isCraftable(BlockMenu inv, ItemStack[] recipe) {
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);
            if ((item != null && item.getAmount() == 1) || !io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(inv.getItemInSlot(getInputSlots()[i]), recipe[i], true))
                return false;
        }
        return true;
    }

    void craft(ItemStack output, BlockMenu inv) {
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);
            if (item != null && item.getType() != Material.AIR) inv.consumeItem(getInputSlots()[i]);
        }
        inv.pushItem(output, getOutputSlots());
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SHEARS);
    }

    @Override
    public int getProgressBarSlot() {
        return 22;
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new java.util.ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{9, 10, 11, 18, 19, 20, 27, 28, 29};
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public String getMachineIdentifier() {
        return "AUTO_KITCHEN";
    }
}