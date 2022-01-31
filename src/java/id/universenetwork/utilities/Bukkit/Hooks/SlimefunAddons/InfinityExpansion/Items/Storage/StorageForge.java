package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.CraftingBlock;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineRecipeType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class StorageForge extends CraftingBlock {
    public static final MachineRecipeType TYPE = new MachineRecipeType("storage_forge", Storage.STORAGE_FORGE);

    public StorageForge(ItemGroup category, SlimefunItemStack stack, RecipeType type, ItemStack[] recipe) {
        super(category, stack, type, recipe);
        addRecipesFrom(TYPE);
    }

    @Override
    protected void onSuccessfulCraft(BlockMenu menu, ItemStack toOutput) {
        StorageUnit.transferToStack(menu.getItemInSlot(layout.inputSlots()[4]), toOutput);
    }
}