package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Machines;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;

public final class GrowingMachine extends AbstractMachineBlock implements RecipeDisplayItem {
    static final int[] OUTPUT_SLOTS = {13, 14, 15, 16, 22, 23, 24, 25, 31, 32, 33, 34, 40, 41, 42, 43};
    static final int[] INPUT_SLOTS = {37};
    static final int STATUS_SLOT = 10;
    static final ItemStack GROWING = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aGrowing...");
    static final ItemStack INPUT_PLANT = new CustomItemStack(Material.BLUE_STAINED_GLASS_PANE, "&9Input a plant!");
    @Setter
    EnumMap<Material, ItemStack[]> recipes;
    @Setter
    int ticksPerOutput;

    public GrowingMachine(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected boolean process(@NotNull Block b, @NotNull BlockMenu menu) {
        ItemStack input = menu.getItemInSlot(INPUT_SLOTS[0]);
        if (input != null && this.recipes.containsKey(input.getType())) {
            if (menu.hasViewer()) menu.replaceExistingItem(STATUS_SLOT, GROWING);
            if (slimefunTickCount % this.ticksPerOutput == 0) {
                ItemStack[] output = this.recipes.get(input.getType());
                if (output != null) for (ItemStack item : output) menu.pushItem(item.clone(), OUTPUT_SLOTS);
            }
            return true;
        } else {
            if (menu.hasViewer()) menu.replaceExistingItem(STATUS_SLOT, INPUT_PLANT);
            return false;
        }
    }

    @Override
    protected int getStatusSlot() {
        return STATUS_SLOT;
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 9, 10, 11, 18, 19, 20});
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{27, 28, 29, 36, 38, 45, 46, 47});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{3, 4, 5, 6, 7, 8, 12, 17, 21, 26, 30, 35, 39, 44, 48, 49, 50, 51, 52, 53});
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list = new ArrayList<>();
        for (Map.Entry<Material, ItemStack[]> entry : this.recipes.entrySet()) {
            ItemStack in = new ItemStack(entry.getKey());
            for (ItemStack item : entry.getValue()) {
                list.add(in);
                list.add(item);
            }
        }
        return list;
    }

    @Override
    protected int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }
}