package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Strainer;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.TickingMenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util.isWaterLogged;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

@ParametersAreNonnullByDefault
public final class StrainerBase extends TickingMenuBlock implements RecipeDisplayItem {
    static final ItemStack POTATO = new CustomItemStack(Material.POTATO, "&7:&6Potatofish&7:", "&eLucky");
    static final int STATUS_SLOT = 10;
    static final int[] OUTPUT_SLOTS = {13, 14, 15, 16, 22, 23, 24, 25, 31, 32, 33, 34, 40, 41, 42, 43};
    static final int[] INPUT_SLOTS = {37};
    static final ItemStack[] OUTPUTS = {new ItemStack(Material.STICK), new ItemStack(Material.SAND), new ItemStack(Material.GRAVEL), new ItemStack(Material.QUARTZ), new ItemStack(Material.REDSTONE), new ItemStack(Material.EMERALD), new SlimefunItemStack(MAGNESIUM_DUST, 1), new SlimefunItemStack(COPPER_DUST, 1), new SlimefunItemStack(COPPER_DUST, 1), new SlimefunItemStack(SILVER_DUST, 1), new SlimefunItemStack(ALUMINUM_DUST, 1), new SlimefunItemStack(LEAD_DUST, 1), new SlimefunItemStack(IRON_DUST, 1), new SlimefunItemStack(GOLD_DUST, 1), new SlimefunItemStack(TIN_DUST, 1), new SlimefunItemStack(ZINC_DUST, 1),};
    final int time;

    public StrainerBase(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe, int time) {
        super(category, item, type, recipe);
        this.time = time;
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 9, 11, 18, 19, 20});
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{27, 28, 29, 36, 38, 45, 46, 47});
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{3, 4, 5, 6, 7, 8, 12, 17, 21, 26, 30, 35, 39, 44, 48, 49, 50, 51, 52, 53});
        blockMenuPreset.addItem(STATUS_SLOT, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
    }

    @NotNull
    @Override
    public int[] getInputSlots(@NotNull DirtyChestMenu menu, @NotNull ItemStack item) {
        if (Strainer.getStrainer(item) > 0) return INPUT_SLOTS;
        else return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        for (ItemStack output : OUTPUTS) {
            items.add(Materials.BASIC_STRAINER);
            items.add(output);
        }
        return items;
    }

    @NotNull
    @Override
    public String getRecipeSectionLabel(@NotNull Player p) {
        return "&7Collects:";
    }

    @Override
    protected void tick(Block b, BlockMenu inv) {
        //check water
        if (!isWaterLogged(b)) return;

        //check input
        ItemStack strainer = inv.getItemInSlot(INPUT_SLOTS[0]);
        int speed = Strainer.getStrainer(strainer);

        if (speed == 0) {
            if (inv.hasViewer())
                inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.BARRIER, "&cInput a Strainer!"));
            return;
        }
        Random random = ThreadLocalRandom.current();

        //progress
        if (random.nextInt(this.time / speed) != 0) {
            if (inv.hasViewer())
                inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aCollecting..."));
            return;
        }

        //fish
        if (random.nextInt(10000) == 0) inv.pushItem(POTATO, OUTPUT_SLOTS);
        ItemStack output = OUTPUTS[random.nextInt(OUTPUTS.length)];

        //check fits
        if (!inv.fits(output, OUTPUT_SLOTS)) {
            if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, NO_ROOM_ITEM);
            return;
        }

        //output
        inv.pushItem(output.clone(), OUTPUT_SLOTS);
        if (inv.hasViewer())
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aMaterial Collected!"));

        //reduce durability
        if (random.nextInt(strainer.getEnchantmentLevel(Enchantment.DURABILITY) + 3 * strainer.getEnchantmentLevel(Enchantment.MENDING) + 1) == 0) {
            ItemMeta itemMeta = strainer.getItemMeta();
            Damageable durability = (Damageable) itemMeta;
            int current = durability.getDamage();
            if (current + 1 == Material.FISHING_ROD.getMaxDurability()) inv.consumeItem(INPUT_SLOTS[0]);
            else { //reduce
                ((Damageable) itemMeta).setDamage(current + 1);
                strainer.setItemMeta(itemMeta);
                inv.replaceExistingItem(INPUT_SLOTS[0], strainer);
            }
        }
    }
}