package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SoundMuffler;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;
import static org.bukkit.Material.*;

public class SoundMufflerMachine extends SlimefunItem implements io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent {
    static final String ITEM_NAME = "&3Sound Muffler";
    static final String ITEM_ID = "SOUND_MUFFLER";
    public static final int DISTANCE = 8;
    static final int[] border = {1, 2, 3, 4, 5, 6, 7};

    public SoundMufflerMachine() {
        super(SoundMuffler.SOUND_MUFFLER, new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack(ITEM_ID, WHITE_CONCRETE, ITEM_NAME, "", "&7Muffles all sound in a", "&78 block radius", "", "&e\u26A1 Requires power to use"), ITEM_ID, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL)});
        addItemHandler(onPlace());
        new BlockMenuPreset(ITEM_ID, ITEM_NAME) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public void newInstance(me.mrCookieSlime.Slimefun.api.inventory.BlockMenu menu, Block b) {
                int volume = 10;
                boolean enabled = false;
                if (!hasBlockInfo(b) || getLocationInfo(b.getLocation(), "enabled") == null) {
                    addBlockInfo(b, "volume", String.valueOf(volume));
                    addBlockInfo(b, "enabled", String.valueOf(false));
                } else {
                    volume = Integer.parseInt(getLocationInfo(b.getLocation(), "volume"));
                    enabled = Boolean.parseBoolean(getLocationInfo(b.getLocation(), "enabled"));
                }
                menu.replaceExistingItem(8, new CustomItemStack((enabled ? REDSTONE : GUNPOWDER), "&7Enabled: " + (enabled ? "&a\u2714" : "&4\u2718"), "", "&e> Click to enable this Machine"));
                menu.replaceExistingItem(0, new CustomItemStack(PAPER, "&eVolume: &b" + volume, "&7Valid value range: 0-100", "&7L-click: -10", "&7R-click: +10", "&7With shift held: +/-1"));
                int finalVolume = volume;
                menu.addMenuClickHandler(0, (p, arg1, arg2, arg3) -> {
                    int newVolume;
                    if (arg3.isRightClicked()) if (arg3.isShiftClicked())
                        newVolume = Math.min(finalVolume + 1, 100);
                    else
                        newVolume = Math.min(finalVolume + 10, 100);
                    else if (arg3.isShiftClicked())
                        newVolume = Math.max(finalVolume - 1, 0);
                    else
                        newVolume = Math.max(finalVolume - 10, 0);
                    addBlockInfo(b, "volume", String.valueOf(newVolume));
                    newInstance(menu, b);
                    return false;
                });
                menu.addMenuClickHandler(8, (p, arg1, arg2, arg3) -> {
                    String isEnabled = getLocationInfo(b.getLocation(), "enabled");
                    if (isEnabled != null && isEnabled.equals("true"))
                        addBlockInfo(b, "enabled", "false");
                    else
                        addBlockInfo(b, "enabled", "true");
                    newInstance(menu, b);
                    return false;
                });
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager().hasPermission(p, b, io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow flow) {
                return new int[0];
            }
        };
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : border)
            preset.addItem(i, new CustomItemStack(GRAY_STAINED_GLASS_PANE, " "), (player, i1, itemStack, clickAction) -> false);
    }


    io.github.thebusybiscuit.slimefun4.api.items.ItemHandler onPlace() {
        return new io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(org.bukkit.event.block.BlockPlaceEvent e) {
                addBlockInfo(e.getBlock(), "enabled", "false");
                addBlockInfo(e.getBlock(), "volume", "10");
            }
        };
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public int getEnergyConsumption() {
        return 8;
    }

    @Override
    public int getCapacity() {
        return 352;
    }

    @Override
    public void preRegister() {
        addItemHandler(new me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config data) {
                try {
                    SoundMufflerMachine.this.tick(b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void uniqueTick() {
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });

    }

    void tick(Block b) {
        if ((getLocationInfo(b.getLocation(), "enabled").equals("true")) && (getCharge(b.getLocation()) > 8))
            removeCharge(b.getLocation(), getEnergyConsumption());
    }
}