package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Machines;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.StackUtils;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.AllArgsConstructor;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util.getIntData;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public final class SingularityConstructor extends AbstractMachineBlock implements RecipeDisplayItem {
    static final List<Recipe> RECIPE_LIST = new ArrayList<>();
    static final Map<String, Pair<Integer, Recipe>> RECIPE_MAP = new HashMap<>();
    public static final RecipeType TYPE = new RecipeType(new NamespacedKey(plugin, "singularity_constructor"), Machines.SINGULARITY_CONSTRUCTOR, (stacks, itemStack) -> {
        int amt = 0;
        for (ItemStack item : stacks) if (item != null) amt += item.getAmount();
        String id = StackUtils.getIdOrType(stacks[0]);
        Recipe recipe = new Recipe((SlimefunItemStack) itemStack, stacks[0], id, amt);
        RECIPE_LIST.add(recipe);
        RECIPE_MAP.put(id, new Pair<>(RECIPE_LIST.size() - 1, recipe));
    });
    static final String PROGRESS = "progress";
    static final int STATUS_SLOT = 13;
    static final int[] INPUT_SLOT = {10};
    static final int[] OUTPUT_SLOT = {16};
    @Setter
    int speed;

    public SingularityConstructor(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(category, item, type, recipe);
    }

    @Override
    protected void onBreak(@NotNull BlockBreakEvent e, @NotNull BlockMenu menu) {
        super.onBreak(e, menu);
        Location l = menu.getLocation();
        int progress = getIntData(PROGRESS, l);
        Integer progressID = getProgressID(l);
        if (progress > 0 && progressID != null) {
            Recipe triplet = RECIPE_LIST.get(progressID);
            if (triplet != null) {
                ItemStack drop = new CustomItemStack(triplet.input, 64);
                int stacks = progress / 64;
                if (stacks > 0) for (int i = 0; i < stacks; i++) e.getBlock().getWorld().dropItemNaturally(l, drop);
                int remainder = progress % 64;
                if (remainder > 0) {
                    drop.setAmount(remainder);
                    e.getBlock().getWorld().dropItemNaturally(l, drop);
                }
            }
        }
        setProgressID(l, null);
        setProgress(l, 0);
    }

    @Override
    protected boolean process(@NotNull Block b, @NotNull BlockMenu menu) {
        ItemStack input = menu.getItemInSlot(INPUT_SLOT[0]);
        String inputID;
        if (input == null) inputID = null;
        else inputID = StackUtils.getIdOrType(input);

        // load data
        Integer progressID = getProgressID(b.getLocation());
        int progress = getIntData(PROGRESS, b.getLocation());
        Recipe triplet;
        boolean takeCharge = false;
        if (progressID == null || progress == 0) {
            // not started
            if (inputID != null) {
                Pair<Integer, Recipe> pair = RECIPE_MAP.get(inputID);
                if (pair != null) {
                    progress = Math.min(speed, input.getAmount());
                    input.setAmount(input.getAmount() - progress);
                    progressID = pair.getFirstValue();
                    triplet = pair.getSecondValue();
                    takeCharge = true;
                } else triplet = null; // invalid input
            } else triplet = null; // still haven't started
        } else {
            // started
            triplet = RECIPE_LIST.get(progressID);
            if (inputID != null) {
                int max = Math.min(triplet.amount - progress, Math.min(speed, input.getAmount()));
                if (max > 0) if (triplet.id.equals(inputID)) {
                    progress += max;
                    input.setAmount(input.getAmount() - max);
                    takeCharge = true;
                } // invalid input
            }
        }

        // show status and output if done
        if (triplet != null) {
            if (progress >= triplet.amount && menu.fits(triplet.output, OUTPUT_SLOT)) {
                menu.pushItem(triplet.output.clone(), OUTPUT_SLOT);
                progress = 0;
                progressID = null;
                if (menu.hasViewer())
                    menu.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aConstructing " + triplet.output.getDisplayName() + "...", "&7Complete"));
            } else if (menu.hasViewer())
                menu.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aConstructing " + triplet.output.getDisplayName() + "...", "&7" + progress + " / " + triplet.amount));
        } else if (menu.hasViewer()) invalidInput(menu);

        // save data
        setProgressID(b.getLocation(), progressID);
        setProgress(b.getLocation(), progress);
        return takeCharge;
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{0, 1, 2, 9, 11, 18, 19, 20});
        blockMenuPreset.drawBackground(new int[]{3, 4, 5, 12, 13, 14, 21, 22, 23});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{6, 7, 8, 15, 17, 24, 25, 26});
    }

    @Override
    protected int getStatusSlot() {
        return STATUS_SLOT;
    }

    @Override
    protected int[] getInputSlots() {
        return INPUT_SLOT;
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOT;
    }

    @Override
    public void onNewInstance(@NotNull BlockMenu blockMenu, @NotNull Block block) {
        invalidInput(blockMenu);
    }

    static void invalidInput(BlockMenu menu) {
        menu.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.RED_STAINED_GLASS_PANE, "&cInput a valid material to start"));
    }

    static void setProgress(Location l, int progress) {
        BlockStorage.addBlockInfo(l, "progress", String.valueOf(progress));
    }

    static void setProgressID(Location l, @Nullable Integer progressID) {
        if (progressID == null) BlockStorage.addBlockInfo(l, "progressid", null);
        else BlockStorage.addBlockInfo(l, "progressid", String.valueOf(progressID));
    }

    @Nullable
    static Integer getProgressID(Location l) {
        String id = BlockStorage.getLocationInfo(l, "progressid");
        if (id == null) return null;
        else try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            setProgressID(l, null);
            return null;
        }
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        final List<ItemStack> items = new ArrayList<>();
        for (Recipe recipe : RECIPE_LIST) {
            items.add(recipe.input);
            items.add(recipe.output);
        }
        return items;
    }

    @AllArgsConstructor
    static final class Recipe {
        final SlimefunItemStack output;
        final ItemStack input;
        final String id;
        final int amount;
    }
}