package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.SFMobDrops;

import id.universenetwork.utilities.Bukkit.Events.UNUtilitiesReloadConfigEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class SFMobDrops implements org.bukkit.event.Listener {
    static SFMobDrops instance;
    final Set<Drop> drops = new HashSet<>();
    public static boolean enabled;

    public SFMobDrops() {
        if (id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled("SFMobDrops")) {
            instance = this;
            loadConfig();
            id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.Events.registerListener(this, new Guis());
            new MobDropsCommand();
            System.out.println(prefix + " §bSuccessfully Registered §dSFMobDrops §bAddon");
        }
    }

    protected void loadConfig() {
        final Set<Drop> newSet = new HashSet<>();

        // No Inspection Unchecked
        final List<Map<String, Object>> list = (List<Map<String, Object>>) id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Settings("SFMobDrops").getList("Drops");
        if (list == null || list.isEmpty()) return;
        for (Map<String, Object> map : list) {

            // If some arguments are invalid then just miss it.
            if (!validateArguments(map)) continue;
            final EntityType type = entityFromString((String) map.get("entity"));
            final String slimefunId = (String) map.get("slimefunItem");
            final double chance = getDouble(map.get("chance"));
            final String entityName = (String) map.get("name");
            final String nbtTag = (String) map.get("nbtTag");
            final int amount = map.get("amount") == null ? 1 : (int) map.get("amount");
            newSet.add(new Drop(type, slimefunId, chance, entityName, nbtTag, amount));
        }
        drops.clear();
        drops.addAll(newSet);
    }

    @EventHandler
    public void onConfigReload(UNUtilitiesReloadConfigEvent e) {
        loadConfig();
    }

    @EventHandler
    public void onMobDeath(@NotNull EntityDeathEvent e) {
        final Drop drop = findDropFromEntity(e.getEntity());
        if (drop != null && ThreadLocalRandom.current().nextDouble(100) <= drop.getChance()) {
            final SlimefunItem item = SlimefunItem.getById(drop.getSlimefunId());
            if (item != null && !item.isDisabledIn(e.getEntity().getWorld())) {
                final ItemStack dropping = item.getItem().clone();
                dropping.setAmount(drop.getAmount());
                e.getDrops().add(dropping);
            }
        }
    }

    boolean validateArguments(@NotNull Map<String, Object> map) {
        final String entity = (String) map.get("entity");
        final String sfItem = (String) map.get("slimefunItem");
        final double chance = getDouble(map.get("chance"));
        final String nbtTag = (String) map.get("nbtTag");
        final int amount = map.get("amount") == null ? 1 : (int) map.get("amount");

        // Required
        if (entity == null || sfItem == null) {
            plugin.getLogger().warning(prefix + " §6Required property missing! 'entity', 'slimefunItem' and 'chance' are required!");
            return false;
        }
        if (!Constants.CONSTANT.matcher(entity).matches()) {
            plugin.getLogger().warning(prefix + " §6Entity should be in SCREAMING_SNAKE_CASE!");
            return false;
        }
        if (!Constants.CONSTANT.matcher(sfItem).matches()) {
            plugin.getLogger().warning(prefix + " §6Slimefun ID should be in SCREAMING_SNAKE_CASE!");
            return false;
        }
        if (chance < 1 || chance > 100) {
            plugin.getLogger().warning(prefix + " §6Chance is not a valid value! It needs to be between 0-100");
            return false;
        }

        // Not required
        if (nbtTag != null && !Constants.NAMESPACE.matcher(nbtTag).matches()) {
            plugin.getLogger().warning(prefix + " §6The NBT Tag need to be in snake_case!");
            return false;
        }
        if (amount < 1 || amount > 64) {
            plugin.getLogger().warning(prefix + " §6Amount needs to be between 0-64!");
            return false;
        }

        // Validate values
        if (entityFromString(entity) == null) {
            plugin.getLogger().warning(prefix + " §6Invalid entity type value! Given: " + entity + " - valid values here: " + "https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html");
            return false;
        }
        if (SlimefunItem.getById(sfItem) == null) {
            plugin.getLogger().warning(prefix + " §6Invalid Slimefun Item ID! Given: " + sfItem + " - valid values can be found " + "here: https://sf-items.walshy.dev/");
            return false;
        }
        return true;
    }

    @Nullable
    EntityType entityFromString(@NotNull String str) {
        try {
            return EntityType.valueOf(str);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().log(Level.WARNING, prefix + " §6Invalid Entity Type given! {0} is not valid!", str);
            return null;
        }
    }

    @Nullable
    Drop findDropFromEntity(@NotNull LivingEntity entity) {
        for (Drop drop : this.drops)
            if (entity.getType().equals(drop.getDropsFrom())) {
                if (drop.getEntityName() != null && entity.getCustomName() != null && !ChatColors.color(drop.getEntityName()).equals(entity.getCustomName()))
                    continue;
                if (drop.getEntityNbtTag() != null && entity.getPersistentDataContainer().getKeys().stream().noneMatch(key -> key.toString().equals(drop.getEntityNbtTag())))
                    continue;
                return drop;
            }
        return null;
    }

    double getDouble(@NotNull Object obj) {
        return obj instanceof Integer ? (int) obj : (double) obj;
    }

    @NotNull
    public Set<Drop> getDrops() {
        return drops;
    }

    @NotNull
    public static SFMobDrops getInstance() {
        return instance;
    }
}