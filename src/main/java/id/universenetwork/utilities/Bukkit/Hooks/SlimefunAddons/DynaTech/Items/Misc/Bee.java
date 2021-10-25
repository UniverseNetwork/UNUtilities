package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

public class Bee extends io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock {
    int speedMultiplier;

    public Bee(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe, int speedMulitplier) {
        super(itemGroup, item, recipeType, recipe);
        this.speedMultiplier = speedMulitplier;
    }

    public float getSpeedMultipler() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        org.apache.commons.lang.Validate.isTrue(speedMultiplier > 0, "The Speed Multiplier must be greater then 0");
        this.speedMultiplier = speedMultiplier;
    }
}