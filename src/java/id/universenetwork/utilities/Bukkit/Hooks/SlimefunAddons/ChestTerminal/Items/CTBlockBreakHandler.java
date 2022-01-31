package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ChestTerminal.Items;

class CTBlockBreakHandler extends io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler {
    final int[] slots;

    CTBlockBreakHandler(int[] slots) {
        this.slots = slots;
    }

    @Override
    public void onBlockBreak(org.bukkit.block.Block b) {
        me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu = me.mrCookieSlime.Slimefun.api.BlockStorage.getInventory(b);
        if (menu != null) menu.dropItems(b.getLocation(), slots);
    }
}