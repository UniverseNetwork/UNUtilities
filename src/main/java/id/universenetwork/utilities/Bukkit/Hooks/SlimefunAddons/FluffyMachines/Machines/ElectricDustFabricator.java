package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Machines;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FluffyMachines.Utils.Constants;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.multiblocks.OreWasher;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectricDustFabricator extends AContainer implements RecipeDisplayItem {
    public static final int ENERGY_CONSUMPTION = 256;
    public static final int CAPACITY = ENERGY_CONSUMPTION * 3;
    OreWasher oreWasher;
    final List<ItemStack> acceptableInputs = new ArrayList<>(Arrays.asList(new ItemStack(Material.COBBLESTONE), new ItemStack(Material.ANDESITE), new ItemStack(Material.DIORITE), new ItemStack(Material.GRANITE)));

    public ElectricDustFabricator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        oreWasher = (OreWasher) SlimefunItems.ORE_WASHER.getItem();
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>();
        for (SlimefunItemStack dust : Constants.dusts) {
            displayRecipes.add(new CustomItemStack(Material.COBBLESTONE, "&fAny Cobblestone Variant", "&7Cobblestone", "&7Andesite", "&7Diorite", "&7Granite"));
            displayRecipes.add(dust);
        }
        return displayRecipes;
    }

    @Override
    protected MachineRecipe findNextRecipe(BlockMenu menu) {
        for (int slot : getInputSlots()) {
            for (ItemStack acceptableInput : acceptableInputs) {
                if (SlimefunUtils.isItemSimilar(menu.getItemInSlot(slot), acceptableInput, true, false)) {
                    if (!hasFreeSlot(menu)) return null;
                    ItemStack dust = oreWasher.getRandomDust();
                    MachineRecipe recipe = new MachineRecipe(4 / getSpeed(), new ItemStack[]{acceptableInput}, new ItemStack[]{dust});
                    if (menu.fits(recipe.getOutput()[0], getOutputSlots())) {
                        menu.consumeItem(slot);
                        return recipe;
                    }
                }
            }
        }
        return null;
    }

    boolean hasFreeSlot(BlockMenu menu) {
        for (int slot : getOutputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);
            if (item == null || item.getType() == Material.AIR) return true;
        }
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CAULDRON);
    }

    @NotNull
    @Override
    public String getMachineIdentifier() {
        return "DUST_FABRICATOR";
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public int getSpeed() {
        return 10;
    }
}