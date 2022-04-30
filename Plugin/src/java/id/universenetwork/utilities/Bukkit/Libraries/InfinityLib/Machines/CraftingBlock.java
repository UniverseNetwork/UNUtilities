package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemStackSnapshot;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.ChatColor.*;

public class CraftingBlock extends MenuBlock {
    public static final ItemStack CLICK_TO_CRAFT = new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(org.bukkit.Material.LIME_STAINED_GLASS_PANE, "&aClick To Craft!");

    @lombok.Setter
    protected MachineLayout layout = MachineLayout.CRAFTING_DEFAULT;
    final java.util.List<CraftingBlockRecipe> recipes = new java.util.ArrayList<>();

    public CraftingBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup category, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    protected void craft(Block b, BlockMenu menu, org.bukkit.entity.Player p) {
        int[] slots = layout.inputSlots;
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; i++) input[i] = menu.getItemInSlot(slots[i]);
        CraftingBlockRecipe recipe = getOutput(input);
        if (recipe != null) {
            if (recipe.check(p)) if (menu.fits(recipe.output, layout.outputSlots())) {
                ItemStack output = recipe.output.clone();
                onSuccessfulCraft(menu, output);
                menu.pushItem(output, layout.outputSlots());
                recipe.consume(input);
                p.sendMessage(GREEN + "Successfully Crafted: " + io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.getItemName(output));
            } else p.sendMessage(GOLD + "Not Enough Room!");
        } else p.sendMessage(RED + "Invalid Recipe!");
    }

    protected void onSuccessfulCraft(BlockMenu menu, ItemStack toOutput) {
    }

    @Override
    protected void setup(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset) {
        preset.drawBackground(OUTPUT_BORDER, layout.outputBorder());
        preset.drawBackground(INPUT_BORDER, layout.inputBorder());
        preset.drawBackground(BACKGROUND_ITEM, layout.background());
        preset.addItem(layout.statusSlot(), CLICK_TO_CRAFT, io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected void onNewInstance(BlockMenu menu, Block b) {
        menu.addMenuClickHandler(layout.statusSlot(), (player, i, itemStack, clickAction) -> {
            craft(b, menu, player);
            return false;
        });
    }

    public final CraftingBlock addRecipe(ItemStack output, ItemStack... inputs) {
        if (inputs.length == 0) throw new IllegalArgumentException("Cannot add recipe with no input!");
        CraftingBlockRecipe recipe = new CraftingBlockRecipe(output, inputs);
        recipes.add(recipe);
        return this;
    }

    public final CraftingBlock addRecipesFrom(MachineRecipeType recipeType) {
        recipeType.sendRecipesTo((in, out) -> addRecipe(out, in));
        return this;
    }

    protected final CraftingBlockRecipe getOutput(ItemStack[] input) {
        ItemStackSnapshot[] snapshots = ItemStackSnapshot.wrapArray(input);
        for (CraftingBlockRecipe recipe : recipes) if (recipe.check(snapshots)) return recipe;
        return null;
    }

    @Override
    protected final int[] getInputSlots(me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, ItemStack input) {
        return new int[0];
    }

    @Override
    protected final int[] getInputSlots() {
        return layout.inputSlots();
    }

    @Override
    protected final int[] getOutputSlots() {
        return layout.outputSlots();
    }
}