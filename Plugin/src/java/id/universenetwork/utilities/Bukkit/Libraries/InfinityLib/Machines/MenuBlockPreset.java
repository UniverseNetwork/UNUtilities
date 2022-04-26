package id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines;

import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.block.Block;

final class MenuBlockPreset extends me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset {
    final MenuBlock menuBlock;

    MenuBlockPreset(MenuBlock menuBlock) {
        super(menuBlock.getId(), menuBlock.getItemName());
        this.menuBlock = menuBlock;
        menuBlock.setup(this);
    }

    @Override
    public void newInstance(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu, Block b) {
        menuBlock.onNewInstance(menu, b);
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu menu, ItemTransportFlow flow, org.bukkit.inventory.ItemStack item) {
        return menuBlock.getTransportSlots(menu, flow, item);
    }

    @Override
    public void init() {
    }

    @Override
    public boolean canOpen(Block b, org.bukkit.entity.Player p) {
        return io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK) && menuBlock.canUse(p, false);
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
        return new int[0];
    }
}