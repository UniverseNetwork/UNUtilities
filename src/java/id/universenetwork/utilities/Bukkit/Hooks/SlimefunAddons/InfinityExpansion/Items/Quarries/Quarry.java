package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Quarries;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

@ParametersAreNonnullByDefault
public final class Quarry extends AbstractMachineBlock implements RecipeDisplayItem {
    static final boolean ALLOW_NETHER_IN_OVERWORLD = getConfig().getBoolean("quarry-options.output-nether-materials-in-overworld");

    static int INTERVAL() {
        int v = getConfig().getInt("quarry-options.ticks-per-output", 10);
        if (v < 1 || v > 100) {
            getLogger().warning(prefix + " §6ticks-per-output in quarry-options on §dInfinityExpansion §6Addon Settings is less than 1 or greater than 100!");
            return 10;
        }
        return v;
    }

    static final ItemStack MINING = new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aMining...");
    static final ItemStack OSCILLATOR_INFO = new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, "&bOscillator Slot", "&7Place a quarry oscillator to", "&7boost certain material's rates!");
    static final int[] OUTPUT_SLOTS = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    static final int OSCILLATOR_SLOT = 49;
    static final int STATUS_SLOT = 4;
    final int speed;
    final int chance;
    final Material[] outputs;

    public Quarry(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe, int speed, int chance, Material... outputs) {
        super(category, item, type, recipe);
        this.speed = speed;
        this.chance = chance;
        this.outputs = outputs;
    }

    @Override
    protected void setup(@NotNull BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 45, 46, 47, 51, 52, 53});
        blockMenuPreset.addItem(48, OSCILLATOR_INFO, ChestMenuUtils.getEmptyClickHandler());
        blockMenuPreset.addItem(50, OSCILLATOR_INFO, ChestMenuUtils.getEmptyClickHandler());
    }

    @Override
    protected int[] getInputSlots(DirtyChestMenu menu, ItemStack item) {
        return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return new int[]{OSCILLATOR_SLOT};
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    protected boolean process(Block b, BlockMenu inv) {
        if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, MINING);
        if (slimefunTickCount % INTERVAL() != 0) return true;
        ItemStack outputItem;
        if (ThreadLocalRandom.current().nextInt(chance) == 0) {
            Material oscillator = Oscillator.getOscillator(inv.getItemInSlot(OSCILLATOR_SLOT));
            if (oscillator == null || ThreadLocalRandom.current().nextBoolean()) {
                Material outputType = outputs[ThreadLocalRandom.current().nextInt(outputs.length)];
                if (!ALLOW_NETHER_IN_OVERWORLD && b.getWorld().getEnvironment() != World.Environment.NETHER && (outputType == Material.QUARTZ || outputType == Material.NETHERITE_INGOT || outputType == Material.NETHERRACK))
                    outputItem = new ItemStack(Material.COBBLESTONE, speed);
                else outputItem = new ItemStack(outputType, speed);
            } else outputItem = new ItemStack(oscillator, speed);
        } else outputItem = new ItemStack(Material.COBBLESTONE, speed);
        inv.pushItem(outputItem, OUTPUT_SLOTS);
        return true;
    }

    @Override
    protected int getStatusSlot() {
        return STATUS_SLOT;
    }

    @NotNull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.COBBLESTONE, speed));
        for (Material mat : outputs) items.add(new ItemStack(mat, speed));
        return items;
    }

    @NotNull
    @Override
    public String getRecipeSectionLabel(@NotNull Player p) {
        return "&7Mines:";
    }
}