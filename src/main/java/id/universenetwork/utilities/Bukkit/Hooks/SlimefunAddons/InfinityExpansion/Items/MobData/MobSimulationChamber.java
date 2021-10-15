package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.StackUtils;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.AbstractMachineBlock;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.TickingMenuBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.InfinityExpansion.getConfig;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Utils.Util.getIntData;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;

public final class MobSimulationChamber extends TickingMenuBlock implements EnergyNetComponent {
    static double XP_MULTIPLIER() {
        double v = getConfig().getDouble("mob-simulation-options.xp-multiplier", 1.0);
        if (v < 0 || v > 1000) {
            getLogger().warning(prefix + " §6xp-multiplier in mob-simulation-options on §dInfinityExpansion §6Addon Settings is less than 1 or greater than 100!");
            return 1.0;
        }
        return v;
    }

    static final ItemStack NO_CARD = new CustomItemStack(Material.BARRIER, "&cInput a Mob Data Card!");
    static final int CARD_SLOT = 37;
    static final int STATUS_SLOT = 10;
    static final int[] OUTPUT_SLOTS = {13, 14, 15, 16, 22, 23, 24, 25, 31, 32, 33, 34, 40, 41, 42, 43};
    static final int XP_SLOT = 46;
    final int energy;
    final int interval;

    public MobSimulationChamber(ItemGroup category, SlimefunItemStack item, RecipeType type, ItemStack[] recipe, int energy, int interval) {
        super(category, item, type, recipe);
        this.energy = energy;
        this.interval = interval;
    }

    @Override
    protected void onBreak(@NotNull BlockBreakEvent e, @NotNull BlockMenu menu) {
        super.onBreak(e, menu);
        e.getPlayer().giveExp(getIntData("xp", menu.getLocation()));
        BlockStorage.addBlockInfo(menu.getLocation(), "xp", "0");
    }

    @NotNull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public int getCapacity() {
        return this.energy + Math.max(MobDataTier.BOSS.energy, this.energy * 9);
    }

    @Override
    protected void setup(BlockMenuPreset blockMenuPreset) {
        blockMenuPreset.drawBackground(OUTPUT_BORDER, new int[]{3, 4, 5, 6, 7, 8, 12, 17, 21, 26, 30, 35, 39, 44, 48, 49, 50, 51, 52, 53});
        blockMenuPreset.drawBackground(new int[]{0, 1, 2, 9, 11, 18, 19, 20, STATUS_SLOT, XP_SLOT});
        blockMenuPreset.drawBackground(INPUT_BORDER, new int[]{27, 28, 29, 36, 38, 45, 46, 47});
    }

    @NotNull
    @Override
    protected int[] getInputSlots(@NotNull DirtyChestMenu menu, @NotNull ItemStack item) {
        return new int[0];
    }

    @Override
    protected int[] getInputSlots() {
        return new int[]{CARD_SLOT};
    }

    @Override
    protected int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }

    @Override
    public void onNewInstance(@NotNull BlockMenu menu, @NotNull Block b) {
        Location l = b.getLocation();
        if (BlockStorage.getLocationInfo(l, "xp") == null) BlockStorage.addBlockInfo(l, "xp", "O");
        menu.replaceExistingItem(XP_SLOT, makeXpItem(0));
        menu.addMenuClickHandler(XP_SLOT, (p, slot, item, action) -> {
            int xp = getIntData("xp", l);
            if (xp > 0) {
                p.giveExp(xp);
                p.playSound(l, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                BlockStorage.addBlockInfo(l, "xp", "O");
                menu.replaceExistingItem(XP_SLOT, makeXpItem(0));
            }
            return false;
        });
    }

    static ItemStack makeXpItem(int stored) {
        return new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aStored xp: " + stored, "", "&a> Click to claim");
    }

    @Override
    protected void tick(@NotNull Block b, @NotNull BlockMenu inv) {
        ItemStack input = inv.getItemInSlot(CARD_SLOT);
        if (input == null) return;
        MobDataCard card = MobDataCard.CARDS.get(StackUtils.getId(input));
        if (card == null) {
            if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, NO_CARD);
            return;
        }
        int energy = card.tier.energy + this.energy;
        if (getCharge(b.getLocation()) < energy) {
            if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, AbstractMachineBlock.NO_ENERGY_ITEM);
            return;
        }
        removeCharge(b.getLocation(), energy);
        int xp = getIntData("xp", b.getLocation());
        if (inv.hasViewer()) {
            inv.replaceExistingItem(STATUS_SLOT, new CustomItemStack(Material.LIME_STAINED_GLASS_PANE, "&aSimulating... (" + MachineLore.formatEnergy(energy) + " J/s)"));
            inv.replaceExistingItem(XP_SLOT, makeXpItem(xp));
        }
        if (slimefunTickCount % this.interval != 0) return;
        BlockStorage.addBlockInfo(b.getLocation(), "xp", String.valueOf(xp + card.tier.xp));
        ItemStack item = card.drops.getRandom();
        if (inv.fits(item, OUTPUT_SLOTS)) inv.pushItem(item.clone(), OUTPUT_SLOTS);
        else if (inv.hasViewer()) inv.replaceExistingItem(STATUS_SLOT, NO_ROOM_ITEM);
    }
}