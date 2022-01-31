package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK;

@ParametersAreNonnullByDefault
final class MenuBlockPreset extends BlockMenuPreset {
    final MenuBlock menuBlock;

    MenuBlockPreset(MenuBlock menuBlock) {
        super(menuBlock.getId(), menuBlock.getItemName());
        this.menuBlock = menuBlock;
        menuBlock.setup(this);
    }

    @Override
    public void newInstance(BlockMenu menu, Block b) {
        menuBlock.onNewInstance(menu, b);
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
        return menuBlock.getTransportSlots(menu, flow, item);
    }

    @Override
    public void init() {
    }

    @Override
    public boolean canOpen(Block b, Player p) {
        return getProtectionManager().hasPermission(p, b.getLocation(), INTERACT_BLOCK) && menuBlock.canUse(p, false);
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
        return new int[0];
    }
}