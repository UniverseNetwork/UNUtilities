package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SoundMuffler.SoundMuffler.SOUND_MUFFLER;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getProtectionManager;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.INTERACT_BLOCK;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.*;
import static org.bukkit.Material.*;

public class SoundMufflerMachine extends SlimefunItem implements EnergyNetComponent {
    static final String ITEM_NAME = "&3Sound Muffler";
    static final String ITEM_ID = "SOUND_MUFFLER";
    public static final int DISTANCE = 8;
    static final int[] border = {1, 2, 3, 4, 5, 6, 7};

    public SoundMufflerMachine() {
        super(SOUND_MUFFLER, new SlimefunItemStack(ITEM_ID, WHITE_CONCRETE, ITEM_NAME, "", "&7Muffles all sound in a", "&78 block radius", "", "&e\u26A1 Requires power to use"), ITEM_ID, ENHANCED_CRAFTING_TABLE, new ItemStack[]{new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL), SlimefunItems.STEEL_PLATE, new ItemStack(WHITE_WOOL)});
        addItemHandler(onPlace());
        new BlockMenuPreset(ITEM_ID, ITEM_NAME) {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            @ParametersAreNonnullByDefault
            public void newInstance(final BlockMenu menu, final Block b) {
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
                final int finalVolume = volume;
                menu.addMenuClickHandler(0, (p, arg1, arg2, arg3) -> {
                    int newVolume;
                    if (arg3.isRightClicked()) {
                        if (arg3.isShiftClicked()) newVolume = Math.min(finalVolume + 1, 100);
                        else newVolume = Math.min(finalVolume + 10, 100);
                    } else {
                        if (arg3.isShiftClicked()) newVolume = Math.max(finalVolume - 1, 0);
                        else newVolume = Math.max(finalVolume - 10, 0);
                    }
                    addBlockInfo(b, "volume", String.valueOf(newVolume));
                    newInstance(menu, b);
                    return false;
                });
                menu.addMenuClickHandler(8, (p, arg1, arg2, arg3) -> {
                    final String isEnabled = getLocationInfo(b.getLocation(), "enabled");
                    if (isEnabled != null && isEnabled.equals("true")) addBlockInfo(b, "enabled", "false");
                    else addBlockInfo(b, "enabled", "true");
                    newInstance(menu, b);
                    return false;
                });
            }

            @Override
            @ParametersAreNonnullByDefault
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || getProtectionManager().hasPermission(p, b, INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }
        };
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : border)
            preset.addItem(i, new CustomItemStack(GRAY_STAINED_GLASS_PANE, " "), (player, i1, itemStack, clickAction) -> false);
    }

    ItemHandler onPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(@NotNull BlockPlaceEvent e) {
                addBlockInfo(e.getBlock(), "enabled", "false");
                addBlockInfo(e.getBlock(), "volume", "10");
            }
        };
    }

    @NotNull
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
        addItemHandler(new BlockTicker() {
            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
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