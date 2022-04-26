package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import org.bukkit.inventory.ItemStack;

public final class MachineBlock extends AbstractMachineBlock {
    @lombok.Setter
    protected MachineLayout layout = MachineLayout.MACHINE_DEFAULT;
    final java.util.List<MachineBlockRecipe> recipes = new java.util.ArrayList<>();
    int ticksPerOutput = -1;

    public MachineBlock(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup category, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    public MachineBlock addRecipe(ItemStack output, ItemStack... inputs) {
        if (inputs.length == 0) throw new IllegalArgumentException("Cannot add recipe with no input!");
        MachineBlockRecipe recipe = new MachineBlockRecipe(output, inputs);
        recipes.add(recipe);
        return this;
    }

    public MachineBlock addRecipesFrom(MachineRecipeType recipeType) {
        recipeType.sendRecipesTo((in, out) -> addRecipe(out, in));
        return this;
    }

    public MachineBlock ticksPerOutput(int ticks) {
        if (ticks < 1) throw new IllegalArgumentException("Ticks Per Output must be at least 1!");
        ticksPerOutput = ticks;
        return this;
    }

    @Override
    protected void setup(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset) {
        preset.drawBackground(OUTPUT_BORDER, layout.outputBorder());
        preset.drawBackground(INPUT_BORDER, layout.inputBorder());
        preset.drawBackground(BACKGROUND_ITEM, layout.background());
        preset.addItem(layout.statusSlot(), IDLE_ITEM, io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots() {
        return layout.inputSlots();
    }

    @Override
    protected int[] getOutputSlots() {
        return layout.outputSlots();
    }

    @Override
    public void preRegister() {
        if (ticksPerOutput == -1)
            throw new IllegalStateException("You must call .ticksPerOutput() before registering!");
        super.preRegister();
    }

    @Override
    protected boolean process(org.bukkit.block.Block b, me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu) {
        //if (slimefunTickCount % ticksPerOutput != 0) return true;
        int[] slots = layout.inputSlots();
        ItemStack[] input = new ItemStack[slots.length];
        for (int i = 0; i < slots.length; i++) input[i] = menu.getItemInSlot(slots[i]);
        MachineBlockRecipe recipe = getOutput(input);
        if (recipe != null) {
            ItemStack rem = menu.pushItem(recipe.output.clone(), layout.outputSlots());
            if (rem == null || rem.getAmount() < recipe.output.getAmount()) {
                recipe.consume();
                if (menu.hasViewer()) menu.replaceExistingItem(getStatusSlot(), PROCESSING_ITEM);
                return true;
            } else {
                if (menu.hasViewer()) menu.replaceExistingItem(getStatusSlot(), NO_ROOM_ITEM);
                return false;
            }
        }
        if (menu.hasViewer()) menu.replaceExistingItem(getStatusSlot(), IDLE_ITEM);
        return false;
    }

    MachineBlockRecipe getOutput(ItemStack[] items) {
        java.util.Map<String, MachineInput> map = new java.util.HashMap<>(2, 1F);
        for (ItemStack item : items)
            if (item != null) {
                String string = id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.StackUtils.getId(item);
                if (string == null) string = item.getType().name();
                map.compute(string, (str, input) -> input == null ? new MachineInput(item) : input.add(item));
            }
        for (MachineBlockRecipe recipe : recipes) if (recipe.check(map)) return recipe;
        return null;
    }

    @Override
    protected int getStatusSlot() {
        return layout.statusSlot();
    }
}