package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Generators;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ChippingGenerator extends id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts.AMachineGenerator {
    final ItemStack progressBar = new ItemStack(org.bukkit.Material.WOODEN_AXE);

    public ChippingGenerator(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(org.bukkit.Location l, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
        me.mrCookieSlime.Slimefun.api.inventory.BlockMenu inv = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(l.getBlock());
        int julesAmount;
        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            // Do as many lightweight checks as possible before we do the intensive stuff
            if (item != null && !item.getType().isAir() && item.getType().isItem() && item.hasItemMeta()) {
                // `getItemMeta` does multiple clones! Even doing this once is slow, nevermind multiple times!
                org.bukkit.inventory.meta.ItemMeta meta = item.getItemMeta();
                if (meta instanceof Damageable && !meta.isUnbreakable()) {
                    Damageable im = (Damageable) meta;
                    if (!im.hasDamage()) {
                        int powerPerDurability = 8;
                        julesAmount = item.getType().getMaxDurability() * powerPerDurability;
                        if (julesAmount != 0) {
                            inv.consumeItem(slot);
                            return julesAmount;
                        }
                    }
                    julesAmount = (item.getType().getMaxDurability() - im.getDamage()) * 2;
                    if (julesAmount != 0) {
                        inv.consumeItem(slot);
                        return julesAmount;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public String getMachineIdentifier() {
        return "CHIPPING_GENERATOR";
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public ItemStack getProgressBar() {
        return progressBar;
    }
}