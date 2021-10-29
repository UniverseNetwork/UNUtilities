package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Items;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Handlers.ItemInteractHandler;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.MoreTools.Messages;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.CargoManager;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.ReactorAccessPort;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.TrashCan;
import io.github.thebusybiscuit.slimefun4.implementation.items.electric.EnergyRegulator;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.ColoredMaterial;
import io.github.thebusybiscuit.slimefun4.utils.HeadTexture;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimefunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Manager.Config.get;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.*;
import static io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction.BREAK_BLOCK;

public class CrescentHammer extends SimpleSlimefunItem<ItemInteractHandler> implements DamageableItem {
    final boolean isChestTerminalInstalled = getIntegrations().isChestTerminalInstalled();
    final boolean damageable;
    final boolean rotationEnabled;
    final boolean channelChangeEnabled;
    final int cooldown;
    final List<String> whitelist;
    final HashMap<UUID, Long> lastUses = new HashMap<>();
    final HashMap<String, Integer> slotCurrents = new HashMap<>();

    public CrescentHammer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        ConfigurationSection config = get().getConfigurationSection(ADDONSSETTINGS.getConfigPath() + "MoreTools.Item-Settings.Crescent-Hammer");
        damageable = config.getBoolean("Damageable");
        rotationEnabled = config.getBoolean("Features.Enable-Rotation");
        channelChangeEnabled = config.getBoolean("Features.Enable-Rotation");
        cooldown = config.getInt("Cooldown");
        whitelist = config.getStringList("Rotation-Whitelist");
        slotCurrents.put("CARGO_NODE_INPUT", 42);
        slotCurrents.put("CARGO_NODE_OUTPUT", 13);
        slotCurrents.put("CARGO_NODE_OUTPUT_ADVANCED", 42);
    }

    @NotNull
    @Override
    public ItemInteractHandler getItemHandler() {
        return (e, sfItem) -> {
            if (!sfItem.getId().equals(getId())) return;
            e.setCancelled(true);
            Block b = e.getClickedBlock();
            if (b != null) {
                Player p = e.getPlayer();
                if (getProtectionManager().hasPermission(p, b.getLocation(), BREAK_BLOCK)) {
                    Long lastUse = lastUses.get(p.getUniqueId());
                    if (lastUse != null) if ((System.currentTimeMillis() - lastUse) < cooldown) {
                        p.sendMessage(Messages.CRESCENTHAMMER_COOLDOWN.getMessage().replace("{left-cooldown}", String.valueOf(cooldown - (System.currentTimeMillis() - lastUse))));
                        return;
                    }
                    lastUses.put(p.getUniqueId(), System.currentTimeMillis());
                    switch (e.getAction()) {
                        case RIGHT_CLICK_BLOCK:
                            if (p.isSneaking()) {
                                alterChannel(b, p, -1);
                            } else rotateBlock(b, p);
                            break;
                        case LEFT_CLICK_BLOCK:
                            if (p.isSneaking()) {
                                alterChannel(b, p, 1);
                            } else {
                                dismantleBlock(b, p, e.getItem());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            e.setCancelled(true);
        };
    }

    void alterChannel(Block b, Player p, int change) {
        if (!channelChangeEnabled) return;
        SlimefunItem sfItem = BlockStorage.check(b);
        if (sfItem != null) {
            if (sfItem.getId().startsWith("CARGO_NODE_")) {
                String frequency = BlockStorage.getLocationInfo(b.getLocation(), "frequency");
                if (frequency != null) {
                    int current = Integer.parseInt(frequency);
                    current += change;
                    if (current < 0) current = isChestTerminalInstalled ? 16 : 15;
                    else if (isChestTerminalInstalled && current > 16) current = 0;
                    else if (!isChestTerminalInstalled && current > 15) current = 0;
                    BlockMenu menu = BlockStorage.getInventory(b);
                    int slotCurrent = slotCurrents.get(sfItem.getId());
                    if (current == 16)
                        menu.replaceExistingItem(slotCurrent, new CustomItemStack(HeadTexture.CHEST_TERMINAL.getAsItemStack(), "&bChannel ID: &3" + (current + 1)));
                    else
                        menu.replaceExistingItem(slotCurrent, new CustomItemStack(ColoredMaterial.WOOL.get(current), "&bChannel ID: &3" + (current + 1)));
                    menu.addMenuClickHandler(slotCurrent, ChestMenuUtils.getEmptyClickHandler());
                    BlockStorage.addBlockInfo(b.getLocation(), "frequency", Integer.toString(current));
                    p.sendMessage(Messages.CRESCENTHAMMER_CHANNELCHANGESUCCESS.getMessage().replace("{channel}", Integer.toString(current + 1)));
                    return;
                }
            }
        }
        p.sendMessage(Messages.CRESCENTHAMMER_CHANNELCHANGEFAIL.getMessage());
    }

    void dismantleBlock(Block b, Player p, ItemStack item) {
        SlimefunItem sfItem = BlockStorage.check(b);
        if (sfItem != null) {
            if (sfItem instanceof EnergyNetComponent || sfItem instanceof EnergyRegulator || sfItem.getId().startsWith("CARGO_NODE") || sfItem instanceof CargoManager || sfItem instanceof ReactorAccessPort || sfItem instanceof TrashCan) {
                BlockBreakEvent event = new BlockBreakEvent(b, p);
                Bukkit.getPluginManager().callEvent(event);
                if (!event.isCancelled()) {
                    b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
                    if (isDamageable()) damageItem(p, item);
                    return;
                }
            }
        }
        p.sendMessage(Messages.CRESCENTHAMMER_DISMANTLEFAIL.getMessage());
    }

    void rotateBlock(Block b, Player p) {
        if (!rotationEnabled) return;
        if (whitelist != null && !p.hasPermission("moretools.items.crescent-hammer.rotation-whitelist-bypass"))
            if (!whitelist.contains(b.getType().name())) {
                p.sendMessage(Messages.CRESCENTHAMMER_ROTATEFAIL.getMessage());
                return;
            }
        if (b.getBlockData() instanceof Directional) {
            Directional data = (Directional) b.getBlockData();
            BlockFace[] directions = data.getFaces().toArray(new BlockFace[0]);
            for (int i = 0; i < directions.length; i++)
                if (data.getFacing() == directions[i]) {
                    i = (i == directions.length - 1) ? 0 : i + 1;
                    data.setFacing(directions[i]);
                    b.setBlockData(data, true);

                    // Special management for cargo nodes.
                    if (b.getType() == Material.PLAYER_WALL_HEAD) {
                        SlimefunItem sfItem = BlockStorage.check(b);
                        if (sfItem != null) if (sfItem.getId().startsWith("CARGO_NODE"))
                            getNetworkManager().updateAllNetworks(b.getLocation());
                    }
                    return;
                }
        }
        p.sendMessage(Messages.CRESCENTHAMMER_ROTATEFAIL.getMessage());
    }

    ToolUseHandler getToolUseHandler() {
        return (e, item, i, list) -> {
            if (isItem(item)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Messages.CRESCENTHAMMER_BLOCKBREAKING.getMessage());
            }
        };
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(getToolUseHandler());
    }

    @Override
    public boolean isDamageable() {
        return damageable;
    }
}