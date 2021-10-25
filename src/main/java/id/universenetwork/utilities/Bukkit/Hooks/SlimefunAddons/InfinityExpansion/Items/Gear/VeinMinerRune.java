package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.CoolDowns;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.implementation.items.magical.runes.SoulboundRune;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener;
import static id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Scheduler.run;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

/**
 * A VeinMiner rune, most code from {@link SoulboundRune}
 *
 * @author Mooy1
 */
public final class VeinMinerRune extends SlimefunItem implements Listener, NotPlaceable {
    static final String[] ALLOWED = {"_ORE", "_LOG", "_WOOD", "GILDED", "SOUL", "GRAVEL", "MAGMA", "OBSIDIAN", "DIORITE", "ANDESITE", "GRANITE", "_LEAVES", "GLASS", "DIRT", "GRASS", "DEBRIS", "GLOWSTONE"};
    static final double RANGE = 1.5;
    static final int MAX = 64;
    static final String LORE = ChatColor.AQUA + "Veinminer - Crouch to use";
    static final NamespacedKey key = new NamespacedKey(plugin, "vein_miner");
    final CoolDowns cooldowns = new CoolDowns(1000);
    Block processing;

    public VeinMinerRune(ItemGroup itemGroup, SlimefunItemStack item, RecipeType type, ItemStack[] recipe) {
        super(itemGroup, item, type, recipe);
        registerListener(this);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (isItem(e.getItemDrop().getItemStack()) && e.getItemDrop().getItemStack().getAmount() == 1)
            run(20, () -> activate(e.getPlayer(), e.getItemDrop()));
    }

    void activate(Player p, Item rune) {
        // Being sure the entity is still valid and not picked up or whatsoever.
        if (!rune.isValid()) return;
        Location l = rune.getLocation();
        Collection<Entity> entities = Objects.requireNonNull(l.getWorld()).getNearbyEntities(l, RANGE, RANGE, RANGE, this::findCompatibleItem);
        Optional<Entity> optional = entities.stream().findFirst();
        if (optional.isPresent()) {
            Item item = (Item) optional.get();
            ItemStack itemStack = item.getItemStack();
            if (itemStack.getAmount() == 1) {
                // This lightning is just an effect, it deals no damage.
                l.getWorld().strikeLightningEffect(l);
                run(10, () -> {
                    // Being sure entities are still valid and not picked up or whatsoever.
                    if (rune.isValid() && item.isValid() && rune.getItemStack().getAmount() == 1) {
                        l.getWorld().createExplosion(l, 0);
                        l.getWorld().playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 0.3F, 1);
                        item.remove();
                        rune.remove();
                        setVeinMiner(itemStack, true);
                        l.getWorld().dropItemNaturally(l, itemStack);
                        p.sendMessage(ChatColor.GREEN + "Added Vein Miner to tool!");
                    } else p.sendMessage(ChatColor.RED + "Failed to add vein miner!");
                });
            } else p.sendMessage(ChatColor.RED + "Failed to add vein miner!");
        }
    }

    boolean findCompatibleItem(Entity entity) {
        if (entity instanceof Item) {
            Item item = (Item) entity;
            ItemStack stack = item.getItemStack();
            return stack.getAmount() == 1 && stack.getItemMeta() instanceof Damageable && !isVeinMiner(stack) && !isItem(stack);
        }
        return false;
    }

    public static boolean isVeinMiner(@Nullable ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        return item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BYTE);
    }

    public static void setVeinMiner(@Nullable ItemStack item, boolean makeVeinMiner) {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        boolean isVeinMiner = isVeinMiner(item);
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (makeVeinMiner && !isVeinMiner) {
            container.set(key, PersistentDataType.BYTE, (byte) 1);
            List<String> lore;
            if (meta.hasLore()) lore = meta.getLore();
            else lore = new ArrayList<>();
            lore.add(LORE);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        if (!makeVeinMiner && isVeinMiner) {
            container.remove(key);
            if (meta.hasLore()) {
                List<String> lore = meta.getLore();
                lore.remove(LORE);
                meta.setLore(lore);
                item.setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        if (processing == b) return;
        Player p = e.getPlayer();
        if (!p.isSneaking()) return;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!isVeinMiner(item)) return;
        if (p.getFoodLevel() == 0) {
            p.sendMessage(ChatColor.GOLD + "You are too tired to vein-mine!");
            return;
        }
        String type = b.getType().toString();
        if (!isAllowed(type)) return;
        Location l = b.getLocation();
        if (BlockStorage.hasBlockInfo(l)) return;
        if (!cooldowns.checkAndReset(p.getUniqueId())) {
            p.sendMessage(ChatColor.GOLD + "You must wait 1 second before using again!");
            return;
        }
        Set<Block> found = new HashSet<>();
        Set<Location> checked = new HashSet<>();
        checked.add(l);
        getVein(checked, found, l, b);
        World w = b.getWorld();
        for (Block mine : found) {
            processing = mine;
            BlockBreakEvent event = new BlockBreakEvent(mine, p);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                if (event.isDropItems()) for (ItemStack drop : mine.getDrops(item)) w.dropItemNaturally(l, drop);
                mine.setType(Material.AIR);
            }
        }
        if (type.endsWith("ORE")) w.spawn(b.getLocation(), ExperienceOrb.class).setExperience(found.size() * 2);
        if (ThreadLocalRandom.current().nextBoolean()) {
            FoodLevelChangeEvent event = new FoodLevelChangeEvent(p, p.getFoodLevel() - 1);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) p.setFoodLevel(event.getFoodLevel());
        }
    }

    static boolean isAllowed(String mat) {
        for (String test : ALLOWED) if (mat.contains(test)) return true;
        return false;
    }

    static void getVein(Set<Location> checked, Set<Block> found, Location l, Block b) {
        if (found.size() >= MAX) return;
        for (Location check : getAdjacentLocations(l))
            if (checked.add(check) && check.getBlock().getType().equals(b.getType()) && !BlockStorage.hasBlockInfo(b)) {
                found.add(b);
                getVein(checked, found, check, check.getBlock());
            }
    }

    static List<Location> getAdjacentLocations(Location l) {
        List<Location> list = new ArrayList<>();
        list.add(l.clone().add(1, 0, 0));
        list.add(l.clone().add(-1, 0, 0));
        list.add(l.clone().add(0, 1, 0));
        list.add(l.clone().add(0, -1, 0));
        list.add(l.clone().add(0, 0, 1));
        list.add(l.clone().add(0, 0, -1));
        Collections.shuffle(list);
        return list;
    }
}