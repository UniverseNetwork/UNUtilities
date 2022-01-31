package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import org.bukkit.inventory.ItemStack;

public class WirelessCharger extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachine {
    final double radius;

    public WirelessCharger(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe, double radius) {
        super(itemGroup, item, recipeType, recipe);
        this.radius = radius;
    }

    @Override
    public void tick(org.bukkit.block.Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) return;
        for (org.bukkit.entity.Player p : b.getWorld().getPlayers()) {
            if (p.getLocation().distance(b.getLocation()) <= radius) for (ItemStack item : p.getInventory()) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                if (sfItem instanceof Rechargeable) {
                    Rechargeable rcItem = (Rechargeable) sfItem;
                    if (rcItem.getItemCharge(item) != rcItem.getMaxItemCharge(item)) {
                        removeCharge(b.getLocation(), getEnergyConsumption());
                        rcItem.addItemCharge(item, getEnergyConsumption());
                        p.updateInventory();
                    }
                }
            }
        }
    }

    @Override
    public String getMachineIdentifier() {
        return "WIRElESS_CHARGER";
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(org.bukkit.Material.END_ROD);
    }
}