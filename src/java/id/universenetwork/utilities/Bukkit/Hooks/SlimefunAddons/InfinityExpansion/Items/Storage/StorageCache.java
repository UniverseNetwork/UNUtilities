package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Storage;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Machines.MachineLore;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import lombok.Setter;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.slimefunTickCount;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.run;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.addBlockInfo;
import static me.mrCookieSlime.Slimefun.api.BlockStorage.getLocationInfo;

final class StorageCache {
    /* Menu strings */
    static final String EMPTY_DISPLAY_NAME = ChatColor.WHITE + "Empty";
    static final String VOID_EXCESS_TRUE = ChatColors.color("&7Void Excess:&e true");
    static final String VOID_EXCESS_FALSE = ChatColors.color("&7Void Excess:&e false");

    /* BlockStorage keys */
    static final String STORED_AMOUNT = "stored"; // amount key in block data
    static final String VOID_EXCESS = "void_excess"; // void excess true or null key

    /* Menu Items */
    static final ItemStack EMPTY_ITEM = new CustomItemStack(Material.BARRIER, meta -> {
        meta.setDisplayName(ChatColor.WHITE + "Empty");
        meta.getPersistentDataContainer().set(StorageUnit.EMPTY_KEY, PersistentDataType.BYTE, (byte) 1);
    });

    /* Space Pattern for Sign Display Names */
    static final Pattern SPACE = Pattern.compile(" ");

    /* Instance Constants */
    final StorageUnit storageUnit;
    final BlockMenu menu;

    /* Instance Variables */
    final String[] signDisplay = new String[2];
    String displayName;
    Material material;
    ItemMeta meta;
    boolean voidExcess;
    @Setter
    int amount;

    StorageCache(StorageUnit storageUnit, BlockMenu menu) {
        this.storageUnit = storageUnit;
        this.menu = menu;

        // load data
        reloadData();

        if (isEmpty()) {
            // empty
            setEmptyDisplayName();
            menu.replaceExistingItem(StorageUnit.DISPLAY_SLOT, EMPTY_ITEM);
        } else {
            // something is stored
            ItemStack display = menu.getItemInSlot(StorageUnit.DISPLAY_SLOT);
            if (display != null) {
                ItemMeta copy = display.getItemMeta();
                // fix if they somehow store the empty item
                if (copy.getPersistentDataContainer().has(StorageUnit.EMPTY_KEY, PersistentDataType.BYTE)) {
                    // attempt to recover the correct item from output
                    ItemStack output = menu.getItemInSlot(StorageUnit.OUTPUT_SLOT);
                    if (output != null) {
                        setStored(output);
                        menu.replaceExistingItem(StorageUnit.OUTPUT_SLOT, null);
                    } else {
                        // no output to recover
                        menu.replaceExistingItem(StorageUnit.DISPLAY_SLOT, EMPTY_ITEM);
                        setEmptyDisplayName();
                        amount = 0;
                    }
                    // load the item in menu
                } else load(display, copy);
            }
        }

        // void excess handler
        menu.addMenuClickHandler(StorageUnit.STATUS_SLOT, (p, slot, item, action) -> {
            voidExcess = !voidExcess;
            addBlockInfo(menu.getLocation(), VOID_EXCESS, voidExcess ? "true" : null);
            ItemMeta meta = item.getItemMeta();
            List<String> lore = meta.getLore();
            lore.set(1, voidExcess ? VOID_EXCESS_TRUE : VOID_EXCESS_FALSE);
            meta.setLore(lore);
            item.setItemMeta(meta);
            return false;
        });

        // interact handler
        menu.addMenuClickHandler(StorageUnit.INTERACT_SLOT, (p, slot, item, action) -> {
            if (amount == 1) {
                if (action.isShiftClicked() && !action.isRightClicked()) depositAll(p);
                else withdrawLast(p);
            } else if (!isEmpty()) {
                if (action.isRightClicked()) {
                    if (action.isShiftClicked()) withdraw(p, amount - 1);
                    else withdraw(p, Math.min(material.getMaxStackSize(), amount - 1));
                } else {
                    if (action.isShiftClicked()) depositAll(p);
                    else withdraw(p, 1);
                }
            }
            return false;
        });

        // load status slot
        updateStatus();
    }

    void setDisplayName(String name) {
        displayName = name;
        int len = name.length();
        if (len == 0) {
            signDisplay[0] = "";
            signDisplay[1] = "";
            return;
        }
        String color;
        if (len >= 2 && name.charAt(0) == ChatColor.COLOR_CHAR) {
            char second = name.charAt(1);
            if (len >= 14 && second == 'x') color = name.substring(0, 14);
            else color = new String(new char[]{ChatColor.COLOR_CHAR, second});
        } else color = null;
        if (name.length() <= 15) {
            signDisplay[0] = color != null ? name : ChatColor.WHITE + name;
            signDisplay[1] = "";
            return;
        }
        String[] words = SPACE.split(name);
        int i = 1;
        StringBuilder firstLine = new StringBuilder();
        if (color == null) firstLine.append(ChatColor.WHITE);
        firstLine.append(words[0]);
        while (i < words.length && words[i].length() + firstLine.length() < 15)
            firstLine.append(' ').append(words[i++]);
        signDisplay[0] = firstLine.toString();
        if (i < words.length) {
            StringBuilder secondLine = new StringBuilder();
            String first = words[i++];
            if (first.length() <= 1 || first.charAt(0) != ChatColor.COLOR_CHAR) {
                if (color == null) secondLine.append(ChatColor.WHITE);
                else secondLine.append(color);
            }
            secondLine.append(first);
            while (i < words.length) secondLine.append(' ').append(words[i++]);
            signDisplay[1] = secondLine.toString();
        } else signDisplay[1] = "";
    }

    void setEmptyDisplayName() {
        displayName = EMPTY_DISPLAY_NAME;
        signDisplay[0] = EMPTY_DISPLAY_NAME;
        signDisplay[1] = "";
    }

    void destroy(Location l, BlockBreakEvent e, List<ItemStack> drops) {
        // add output slot
        ItemStack output = menu.getItemInSlot(StorageUnit.OUTPUT_SLOT);
        if (output != null && matches(output)) {
            int add = Math.min(storageUnit.max - amount, output.getAmount());
            if (add != 0) {
                amount += add;
                output.setAmount(output.getAmount() - add);
            }
        }
        ItemStack drop = storageUnit.getItem().clone();
        drop.setItemMeta(StorageUnit.saveToStack(drop.getItemMeta(), menu.getItemInSlot(StorageUnit.DISPLAY_SLOT), displayName, amount));
        e.getPlayer().sendMessage(ChatColor.GREEN + "Stored items transferred to dropped item");
        drops.add(drop);
    }

    void reloadData() {
        Config config = getLocationInfo(menu.getLocation());
        String amt = config.getString(STORED_AMOUNT);
        if (amt == null) {
            amount = 0;
            run(() -> addBlockInfo(menu.getLocation(), STORED_AMOUNT, "0"));
        } else amount = Integer.parseInt(amt);
        voidExcess = "true".equals(config.getString(VOID_EXCESS));
    }

    void load(ItemStack stored, ItemMeta copy) {
        menu.replaceExistingItem(StorageUnit.DISPLAY_SLOT, stored);

        // remove the display key from copy
        copy.getPersistentDataContainer().remove(StorageUnit.DISPLAY_KEY);

        // check if the copy has anything besides the display key
        if (copy.equals(Bukkit.getItemFactory().getItemMeta(stored.getType()))) meta = null;
        else meta = copy;
        setDisplayName(ItemUtils.getItemName(stored));
        material = stored.getType();
    }

    void input() {
        ItemStack input = menu.getItemInSlot(StorageUnit.INPUT_SLOT);
        if (input == null) return;
        if (isEmpty()) {
            // set the stored item to input
            amount = input.getAmount();
            setStored(input);
            menu.replaceExistingItem(StorageUnit.INPUT_SLOT, null, false);
        } else if (matches(input)) {
            if (voidExcess) {
                // input and void excess
                if (amount < storageUnit.max)
                    amount = Math.min(amount + input.getAmount(), storageUnit.max);
                input.setAmount(0);
            } else if (amount < storageUnit.max) {
                // input as much as possible
                if (input.getAmount() + amount >= storageUnit.max) {
                    // last item
                    input.setAmount(input.getAmount() - (storageUnit.max - amount));
                    amount = storageUnit.max;
                } else {
                    amount += input.getAmount();
                    input.setAmount(0);
                }
            }
        }
    }

    void output() {
        if (amount == 0) return;
        ItemStack outputSlot = menu.getItemInSlot(StorageUnit.OUTPUT_SLOT);
        if (outputSlot == null) {
            if (amount == 1) {
                menu.replaceExistingItem(StorageUnit.OUTPUT_SLOT, createItem(1), false);
                setEmpty();
            } else {
                int amt = Math.min(material.getMaxStackSize(), amount - 1);
                menu.replaceExistingItem(StorageUnit.OUTPUT_SLOT, createItem(amt), false);
                amount -= amt;
            }
        } else if (amount > 1) {
            int amt = Math.min(material.getMaxStackSize() - outputSlot.getAmount(), amount - 1);
            if (amt != 0 && matches(outputSlot)) {
                outputSlot.setAmount(outputSlot.getAmount() + amt);
                amount -= amt;
            }
        }
    }

    void tick(Block block) {
        // input output
        input();
        output();

        // store amount
        addBlockInfo(menu.getLocation(), STORED_AMOUNT, String.valueOf(amount));

        // status
        if (menu.hasViewer()) updateStatus();

        // sings
        if (slimefunTickCount % 20 == 0) {
            Block check = block.getRelative(0, 1, 0);
            if (SlimefunTag.SIGNS.isTagged(check.getType()) || checkWallSign(check = block.getRelative(1, 0, 0), block) || checkWallSign(check = block.getRelative(-1, 0, 0), block) || checkWallSign(check = block.getRelative(0, 0, 1), block) || checkWallSign(check = block.getRelative(0, 0, -1), block)) {
                Sign sign = (Sign) check.getState();
                sign.setLine(0, signDisplay[0]);
                sign.setLine(1, signDisplay[1]);
                sign.setLine(2, ChatColor.GRAY + "------------");
                sign.setLine(3, ChatColor.YELLOW.toString() + amount);
                sign.update();
            }
        }
    }

    void updateStatus() {
        menu.replaceExistingItem(StorageUnit.STATUS_SLOT, new CustomItemStack(Material.CYAN_STAINED_GLASS_PANE, meta -> {
            meta.setDisplayName(ChatColor.AQUA + "Status");
            List<String> lore = new ArrayList<>();
            if (amount == 0)
                lore.add(ChatColors.color("&6Stored: &e0 / " + MachineLore.format(storageUnit.max) + " &7(0%)"));
            else
                lore.add(ChatColors.color("&6Stored: &e" + MachineLore.format(amount) + " / " + MachineLore.format(storageUnit.max) + " &7(" + MachineLore.format((double) amount * 100.D / storageUnit.max) + "%)"));
            lore.add(voidExcess ? VOID_EXCESS_TRUE : VOID_EXCESS_FALSE);
            lore.add(ChatColor.GRAY + "(Click to toggle)");
            meta.setLore(lore);
        }), false);
    }

    static boolean checkWallSign(Block sign, Block block) {
        return SlimefunTag.WALL_SIGNS.isTagged(sign.getType()) && sign.getRelative(((WallSign) sign.getBlockData()).getFacing().getOppositeFace()).equals(block);
    }

    void setStored(ItemStack input) {
        meta = input.hasItemMeta() ? input.getItemMeta() : null;
        setDisplayName(ItemUtils.getItemName(input));
        material = input.getType();

        // add the display key to the display input and set amount 1
        ItemMeta meta = input.getItemMeta();
        meta.getPersistentDataContainer().set(StorageUnit.DISPLAY_KEY, PersistentDataType.BYTE, (byte) 1);
        input.setItemMeta(meta);
        input.setAmount(1);
        menu.replaceExistingItem(StorageUnit.DISPLAY_SLOT, input);
    }

    void setEmpty() {
        setEmptyDisplayName();
        meta = null;
        material = null;
        menu.replaceExistingItem(StorageUnit.DISPLAY_SLOT, EMPTY_ITEM);
        amount = 0;
    }

    boolean matches(ItemStack item) {
        return item.getType() == material && item.hasItemMeta() == (meta != null) && (meta == null || meta.equals(item.getItemMeta()));
    }

    ItemStack createItem(int amount) {
        ItemStack item = new ItemStack(material, amount);
        if (meta != null) item.setItemMeta(meta);
        return item;
    }

    boolean isEmpty() {
        return amount == 0;
    }

    void withdraw(Player p, int withdraw) {
        if (material.getMaxStackSize() == 64) {
            ItemStack remaining = p.getInventory().addItem(createItem(withdraw)).get(0);
            if (remaining != null) {
                if (remaining.getAmount() != withdraw) amount += remaining.getAmount() - withdraw;
            } else amount -= withdraw;
            return;
        }
        Inventory inv = p.getInventory();
        int toWithdraw = withdraw;
        do {
            int amt = Math.min(material.getMaxStackSize(), toWithdraw);
            ItemStack remaining = inv.addItem(createItem(amt)).get(0);
            if (remaining != null) {
                toWithdraw -= amt - remaining.getAmount();
                break;
            } else toWithdraw -= amt;
        } while (toWithdraw > 0);
        if (toWithdraw != withdraw) amount += toWithdraw - withdraw;
    }

    void withdrawLast(Player p) {
        if (p.getInventory().addItem(createItem(1)).get(0) == null) setEmpty();
    }

    void depositAll(Player p) {
        if (amount < storageUnit.max) for (ItemStack item : p.getInventory().getStorageContents())
            if (item != null && matches(item)) {
                if (item.getAmount() + amount >= storageUnit.max) {
                    // last item
                    item.setAmount(item.getAmount() - (storageUnit.max - amount));
                    amount = storageUnit.max;
                    break;
                } else {
                    amount += item.getAmount();
                    item.setAmount(0);
                }
            }
    }
}