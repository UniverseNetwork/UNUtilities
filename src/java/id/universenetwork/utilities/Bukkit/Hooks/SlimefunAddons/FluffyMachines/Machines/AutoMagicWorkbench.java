package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AutoMagicWorkbench extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Objects.AutoCrafter {
    public AutoMagicWorkbench(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe, "&6Auto Magic Workbench", Material.BOOKSHELF, "&6Magic Workbench", RecipeType.MAGIC_WORKBENCH);
    }

    /**
     * Modified to accept non-stackable items
     */
    @Override
    public int[] getCustomItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
        if (flow == ItemTransportFlow.WITHDRAW) return getOutputSlots();
        if (item.getType().getMaxStackSize() == 1) return getInputSlots();
        List<Integer> slots = new ArrayList<>();
        for (int slot : getInputSlots()) if (menu.getItemInSlot(slot) != null) slots.add(slot);
        slots.sort(compareSlots(menu));
        int[] array = new int[slots.size()];
        for (int i = 0; i < slots.size(); i++) array[i] = slots.get(i);
        return array;
    }
}