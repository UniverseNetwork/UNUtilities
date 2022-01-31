package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc.Bee;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.DynaTechItems.*;

public class MaterialHive extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine implements io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem, io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive {
    static final int[] BORDER = new int[]{0, 1, 2, 6, 7, 8, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    static final int[] BORDER_IN = new int[]{9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    static final int[] BORDER_OUT = new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    static final int[] BORDER_KEY = new int[]{3, 5, 13};
    static final SlimefunItemStack UI_KEY = new SlimefunItemStack("_UI_KEY", Material.LIGHT_BLUE_STAINED_GLASS_PANE, " ");
    static final int[] INPUT_SLOTS = new int[]{19, 20, 4};
    public final ItemSetting<List<String>> vanillaItemsAccepted = new ItemSetting<>(this, "vanilla-items-accepted", getDefaultAllowedVanillaItems());
    public final ItemSetting<List<String>> slimefunItemsAccepted = new ItemSetting<>(this, "slimefun-items-accepted", getDefaultAllowedSlimefunItems());

    public MaterialHive(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemSetting(vanillaItemsAccepted, slimefunItemsAccepted);
    }

    @Override
    public MachineRecipe findNextRecipe(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu inv) {
        for (MachineRecipe recipe : recipes) {
            ItemStack input = recipe.getInput()[0];
            ItemStack key = inv.getItemInSlot(getInputSlots()[2]);
            if (SlimefunUtils.isItemSimilar(key, input, true) && key.getAmount() == 64) {
                int seconds = 1800;
                ItemStack b1 = inv.getItemInSlot(getInputSlots()[0]);
                ItemStack b2 = inv.getItemInSlot(getInputSlots()[1]);
                if (b1 != null) {
                    SlimefunItem bee1 = SlimefunItem.getByItem(b1);
                    if (bee1 instanceof Bee) seconds -= ((Bee) bee1).getSpeedMultipler() * b1.getAmount();
                }
                if (b2 != null) {
                    SlimefunItem bee2 = SlimefunItem.getByItem(b2);
                    if (bee2 instanceof Bee) seconds -= ((Bee) bee2).getSpeedMultipler() * b2.getAmount();
                    if (SlimefunUtils.isItemSimilar(b1, b2, true) && b1.getAmount() == 64 && b2.getAmount() == 64) {
                        if (bee2.getId().equals(BEE.getItemId())) seconds = 1500;
                        if (bee2.getId().equals(ROBOTIC_BEE.getItemId())) seconds = 900;
                        if (bee2.getId().equals(ADVANCED_ROBOTIC_BEE.getItemId())) seconds = 300;
                    }
                }
                return new MachineRecipe(seconds, recipe.getInput(), recipe.getOutput());
            }
        }
        return null;
    }

    @Override
    public void registerDefaultRecipes() {
        for (String slimefunItem : getDefaultAllowedSlimefunItems()) {
            ItemStack item = SlimefunItem.getById(slimefunItem).getItem().clone();
            item.setAmount(64);
            registerRecipe(new MachineRecipe(1800, new ItemStack[]{item}, new ItemStack[]{SlimefunItem.getById(slimefunItem).getItem()}));
        }
        for (String material : getDefaultAllowedVanillaItems()) {
            ItemStack item = new ItemStack(Material.matchMaterial(material), 64);
            registerRecipe(new MachineRecipe(1800, new ItemStack[]{item}, new ItemStack[]{new ItemStack(Material.matchMaterial(material))}));
        }
    }

    @Override
    public void constructMenu(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset) {
        super.constructMenu(preset);
        preset.drawBackground(UI_KEY, BORDER_KEY);
    }

    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.HIGH;
    }

    @Override
    public String getMachineIdentifier() {
        return "MATERIAL_HIVE";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHERITE_HOE);
    }

    @Override
    public boolean isInputConsumed() {
        return false;
    }

    static List<String> getDefaultAllowedVanillaItems() {
        List<String> materialsAllowed = new ArrayList<>();
        materialsAllowed.add("IRON_INGOT");
        materialsAllowed.add("GOLD_INGOT");
        materialsAllowed.add("NETHERITE_INGOT");
        materialsAllowed.add("DIAMOND");
        materialsAllowed.add("EMERALD");
        materialsAllowed.add("LAPIS_LAZULI");
        materialsAllowed.add("QUARTZ");
        materialsAllowed.add("REDSTONE");
        materialsAllowed.add("COAL");
        return materialsAllowed;
    }

    static List<String> getDefaultAllowedSlimefunItems() {
        List<String> sfItemsAllowed = new ArrayList<>();

        // Ingots
        sfItemsAllowed.add("COPPER_INGOT");
        sfItemsAllowed.add("TIN_INGOT");
        sfItemsAllowed.add("SILVER_INGOT");
        sfItemsAllowed.add("ALUMINUM_INGOT");
        sfItemsAllowed.add("LEAD_INGOT");
        sfItemsAllowed.add("ZINC_INGOT");
        sfItemsAllowed.add("MAGNESIUM_INGOT");

        // Alloys
        sfItemsAllowed.add("STEEL_INGOT");
        sfItemsAllowed.add("DURALUMIN_INGOT");
        sfItemsAllowed.add("BILLON_INGOT");
        sfItemsAllowed.add("BRASS_INGOT");
        sfItemsAllowed.add("ALUMINUM_BRASS_INGOT");
        sfItemsAllowed.add("ALUMINUM_BRONZE_INGOT");
        sfItemsAllowed.add("CORINTHIAN_BRONZE_INGOT");
        sfItemsAllowed.add("SOLDER_INGOT");
        sfItemsAllowed.add("DAMASCUS_STEEL_INGOT");
        sfItemsAllowed.add("HARDENED_METAL_INGOT");
        sfItemsAllowed.add("REINFORCED_ALLOY_INGOT");
        sfItemsAllowed.add("FERROSILICON");
        sfItemsAllowed.add("GILDED_IRON");
        sfItemsAllowed.add("NICKEL_INGOT");
        sfItemsAllowed.add("COBALT_INGOT");

        // Gems
        sfItemsAllowed.add("SYNTHETIC_DIAMOND");
        sfItemsAllowed.add("SYNTHETIC_EMERALD");
        sfItemsAllowed.add("SYNTHETIC_SAPPHIRE");
        sfItemsAllowed.add("CARBONADO");

        return sfItemsAllowed;
    }
}