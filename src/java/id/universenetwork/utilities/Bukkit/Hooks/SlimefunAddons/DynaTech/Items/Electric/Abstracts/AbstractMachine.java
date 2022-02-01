package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Electric.Abstracts;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.*;

public abstract class AbstractMachine extends SlimefunItem implements dev.j3fftw.extrautils.interfaces.InventoryBlock, io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder<CraftingOperation> {
    protected final List<MachineRecipe> recipes = new ArrayList<>();
    final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);
    int processingSpeed = -1;

    @javax.annotation.ParametersAreNonnullByDefault
    protected AbstractMachine(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        processor.setProgressBar(getProgressBar());
        createPreset(this, getInventoryTitle(), this::constructMenu);
        addItemHandler(onBlockBreak());
    }

    @NotNull
    protected io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler onBlockBreak() {
        return new io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler() {
            @Override
            public void onBlockBreak(Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);
                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
                processor.endOperation(b);
            }
        };
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    protected void constructMenu(me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset preset) {
        for (int i : getBorder())
            preset.addItem(i, getBackground(), getEmptyClickHandler());
        for (int i : getInputBorder())
            preset.addItem(i, getInputSlotTexture(), getEmptyClickHandler());
        for (int i : getOutputBorder())
            preset.addItem(i, getOutputSlotTexture(), getEmptyClickHandler());
        preset.addItem(getProgressSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), getEmptyClickHandler());
        for (int i : getOutputSlots())
            preset.addMenuClickHandler(i, new me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(org.bukkit.event.inventory.InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }
            });
    }

    @NotNull
    public String getInventoryTitle() {
        return getItemName();
    }

    public abstract ItemStack getProgressBar();

    public int getSpeed() {
        return processingSpeed;
    }

    public final AbstractMachine setSpeed(int speed) {
        org.apache.commons.lang.Validate.isTrue(speed > 0, "The processing speed must be greater then zero.");
        this.processingSpeed = speed;
        return this;
    }

    @Override
    public void register(@NotNull io.github.thebusybiscuit.slimefun4.api.SlimefunAddon addon) {
        this.addon = addon;
        if (getSpeed() <= 0) {
            warn("The processing speed hasn't been configued correctly. This item will not be registered.");
            warn("Make sure to set it up using" + getClass().getSimpleName() + "#setSpeed(...) before registering");
        }
        registerDefaultRecipes();
        if (getSpeed() > 0) super.register(addon);
    }

    @NotNull
    public abstract String getMachineIdentifier();

    public void registerDefaultRecipes() {
    }

    public List<MachineRecipe> getMachineRecipes() {
        return recipes;
    }

    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);
        for (MachineRecipe recipe : recipes) {
            if (recipe.getInput().length != 1) continue;
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[0]);
        }
        return displayRecipes;
    }

    public boolean IsInputConsumed() {
        return true;
    }

    public int[] getInputSlots() {
        return new int[]{19, 20};
    }

    public int[] getOutputSlots() {
        return new int[]{24, 25};
    }

    public int getProgressSlot() {
        return 22;
    }

    public int[] getBorder() {
        return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44};
    }

    public int[] getInputBorder() {
        return new int[]{9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    }

    public int[] getOutputBorder() {
        return new int[]{14, 15, 16, 17, 23, 26, 32, 33, 34, 35};
    }

    public void registerRecipe(MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / this.getSpeed());
        recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    public void registerRecipe(int seconds, ItemStack input, ItemStack output) {
        registerRecipe(new MachineRecipe(seconds, new ItemStack[]{input}, new ItemStack[]{output}));
    }

    @Override
    public void preRegister() {
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sfItem, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                AbstractMachine.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        CraftingOperation craftingOp = processor.getOperation(b);
        if (craftingOp != null) {
            if (!craftingOp.isFinished()) {
                processor.updateProgressBar(inv, getProgressSlot(), craftingOp);
                craftingOp.addProgress(1);
            } else {
                inv.replaceExistingItem(getProgressSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "));
                for (ItemStack output : craftingOp.getResults()) inv.pushItem(output.clone(), getOutputSlots());
                processor.endOperation(b);
            }
        } else {
            MachineRecipe recipe = findRecipe(inv);
            if (recipe != null) processor.startOperation(b, new CraftingOperation(recipe));
        }
    }

    public MachineRecipe findRecipe(BlockMenu inv) {
        Map<Integer, ItemStack> inventory = new HashMap<>();
        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            if (item != null)
                inventory.put(slot, io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper.wrap(item));
        }
        Map<Integer, Integer> found = new HashMap<>();
        for (MachineRecipe recipe : recipes) {
            for (ItemStack input : recipe.getInput())
                for (int slot : getInputSlots())
                    if (io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar(inventory.get(slot), input, true)) {
                        found.put(slot, input.getAmount());
                        break;
                    }
            if (found.size() == recipe.getInput().length) {
                if (!io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils.fitAll(inv.toInventory(), recipe.getOutput(), getOutputSlots()))
                    return null;
                if (IsInputConsumed()) for (Map.Entry<Integer, Integer> entry : found.entrySet())
                    inv.consumeItem(entry.getKey(), entry.getValue());
                return recipe;
            } else found.clear();
        }
        return null;
    }
}