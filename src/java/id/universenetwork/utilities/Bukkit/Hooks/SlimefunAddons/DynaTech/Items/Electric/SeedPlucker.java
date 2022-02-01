package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class SeedPlucker extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    final ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<>(this, "exotic-garden-integration", true);

    public SeedPlucker(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(exoticGardenIntegration);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(10, new ItemStack[]{new ItemStack(WHEAT)}, new ItemStack[]{new ItemStack(WHEAT_SEEDS)});
        registerRecipe(10, new ItemStack[]{new ItemStack(BEETROOT)}, new ItemStack[]{new ItemStack(BEETROOT_SEEDS)});
        registerRecipe(10, new ItemStack[]{new ItemStack(PUMPKIN)}, new ItemStack[]{new ItemStack(PUMPKIN_SEEDS)});
        registerRecipe(10, new ItemStack[]{new ItemStack(MELON_SLICE)}, new ItemStack[]{new ItemStack(MELON_SEEDS)});
    }

    @Override
    public MachineRecipe findNextRecipe(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu inv) {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTech.isExoticGardenEnabled && exoticGardenIntegration.getValue()) {
            for (int inputSlot : getInputSlots()) {
                SlimefunItem item = SlimefunItem.getByItem(inv.getItemInSlot(inputSlot));
                if (item instanceof id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items.ExoticGardenFruit) {
                    SlimefunItem out = SlimefunItem.getById(item.getId().concat("_BUSH"));
                    if (out != null) {
                        inv.consumeItem(inputSlot);
                        return new MachineRecipe(10, new ItemStack[]{item.getItem()}, new ItemStack[]{out.getItem()});
                    }
                    out = SlimefunItem.getById(item.getId().concat("_SAPLING"));
                    if (out != null) {
                        inv.consumeItem(inputSlot);
                        return new MachineRecipe(10, new ItemStack[]{item.getItem()}, new ItemStack[]{out.getItem()});
                    }
                    out = SlimefunItem.getById(item.getId().concat("_PLANT"));
                    if (out != null) {
                        inv.consumeItem(inputSlot);
                        return new MachineRecipe(10, new ItemStack[]{item.getItem()}, new ItemStack[]{out.getItem()});
                    }
                }
            }
        }
        return super.findNextRecipe(inv);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(HOPPER);
    }

    @Override
    public String getMachineIdentifier() {
        return "SEED_PLUCKER";
    }
}