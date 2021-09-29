package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Machines;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Setter;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public final class MaterialGenerator extends AbstractMachineBlock implements RecipeDisplayItem {
    static final int[] OUTPUT_SLOTS = {13};
    static final int STATUS_SLOT = 4;
    @Setter
    int speed;
    @Setter
    Material material;

    public MaterialGenerator(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(category, item, type, recipe);
    }

    @Override
    protected void setup(@Nonnull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 16, 17});
    }

    @Override
    protected int getStatusSlot() {
        return STATUS_SLOT;
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    public void onNewInstance(@Nonnull BlockMenu menu, @Nonnull Block b) {
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(null);
        items.add(new ItemStack(this.material, this.speed));
        return items;
    }

    @Nonnull
    @Override
    public String getRecipeSectionLabel(@Nonnull Player p) {
        return "&7Generates";
    }

    @Override
    protected boolean process(@Nonnull Block b, @Nonnull BlockMenu inv) {
        ItemStack output = new ItemStack(this.material, this.speed);
        if (!inv.fits(output, OUTPUT_SLOTS)) {
            if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, NO_ROOM_ITEM);
            return false;
        }
        inv.pushItem(output, OUTPUT_SLOTS);
        if (inv.hasViewer())
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aGenerating..."));
        return true;
    }
}