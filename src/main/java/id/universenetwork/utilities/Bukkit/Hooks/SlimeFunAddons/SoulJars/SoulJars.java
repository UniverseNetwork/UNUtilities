package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SoulJars;

import id.universenetwork.utilities.Bukkit.Manager.Config;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.BrokenSpawner;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.RepairedSpawner;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

import static id.universenetwork.utilities.Bukkit.Enums.Features.SlimeFunAddons.ADDONSSETTINGS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Bukkit.getPluginManager;

public class SoulJars {
    static final String JAR_TEXTURE = "bd1c777ee166c47cae698ae6b769da4e2b67f468855330ad7bddd751c5293f";
    final Map<EntityType, Integer> mobs = new EnumMap<>(EntityType.class);
    ItemGroup itemGroup;
    RecipeType recipeType;
    SlimefunItemStack emptyJar;
    boolean silkspawners;

    public SoulJars() {
        silkspawners = getPluginManager().isPluginEnabled("SilkSpawners");
        if (Enabled("SoulJars")) {
            emptyJar = new SlimefunItemStack("SOUL_JAR", JAR_TEXTURE, "&bSoul Jar &7(Empty)", "", "&rKill a Mob while having this", "&rItem in your Inventory to bind", "&rtheir Soul to this Jar");
            itemGroup = new ItemGroup(new NamespacedKey(plugin, "soul_jars"), new CustomItemStack(emptyJar, "&bSoul Jars", "", "&a> Click to open"));
            recipeType = new RecipeType(new NamespacedKey(plugin, "mob_killing"), new CustomItemStack(Material.DIAMOND_SWORD, "&cKill the specified Mob", "&cwhile having an empty Soul Jar", "&cin your Inventory"));
            new SlimefunItem(itemGroup, emptyJar, RecipeType.ANCIENT_ALTAR, new ItemStack[]{EARTH_RUNE, new ItemStack(Material.SOUL_SAND), WATER_RUNE, new ItemStack(Material.SOUL_SAND), NECROTIC_SKULL, new ItemStack(Material.SOUL_SAND), AIR_RUNE, new ItemStack(Material.SOUL_SAND), FIRE_RUNE}, new CustomItemStack(emptyJar, 3)).register(addon);
            new JarsListener(this);
            for (String mob : Config.get().getStringList(ADDONSSETTINGS.getConfigPath() + "SoulJars.Mobs")) {
                try {
                    EntityType type = EntityType.valueOf(mob);
                    registerSoul(type);
                } catch (Exception x) {
                    plugin.getLogger().log(Level.SEVERE, "{0}: Possibly invalid mob type: {1}", new Object[]{x.getClass().getSimpleName(), mob});
                }
            }
            if (silkspawners)
                System.out.println(prefix + " §bSuccessfully Registered §dSoulJars §bAddon With §dSilkSpawners §bSupport");
            else System.out.println(prefix + " §bSuccessfully Registered §dSoulJars §bAddon");
        }
    }

    void registerSoul(EntityType type) {
        String name = ChatUtils.humanize(type.name());
        int souls = Config.get().getInt(ADDONSSETTINGS.getConfigPath() + "SoulJars.Souls-Required." + type, 128);
        mobs.put(type, souls);
        Material mobEgg = Material.getMaterial(type + "_SPAWN_EGG");
        if (mobEgg == null) mobEgg = Material.ZOMBIE_SPAWN_EGG;

        // @formatter:off
        SlimefunItemStack jarItem = new SlimefunItemStack(type.name() + "_SOUL_JAR", JAR_TEXTURE, "&cSoul Jar &7(" + name + ")", "", "&7Infused Souls: &e1");
        SlimefunItem jar = new UnplaceableBlock(itemGroup, jarItem, recipeType, new ItemStack[]{null, null, null, emptyJar, null, new CustomItemStack(mobEgg, "&rKill " + souls + "x " + name), null, null, null});
        jar.register(addon);
        SlimefunItemStack filledJarItem = new SlimefunItemStack("FILLED_" + type.name() + "_SOUL_JAR", JAR_TEXTURE, "&cFilled Soul Jar &7(" + name + ")", "", "&7Infused Souls: &e" + souls);
        SlimefunItem filledJar = new FilledJar(itemGroup, filledJarItem, recipeType, new ItemStack[]{null, null, null, emptyJar, null, new CustomItemStack(mobEgg, "&rKill " + souls + "x " + name), null, null, null});
        filledJar.register(addon);
        ItemStack reinforcedSpawner;
        SlimefunItemStack brokenSpawner = new SlimefunItemStack(type + "_BROKEN_SPAWNER", Material.SPAWNER, "&cBroken Spawner", "&7Type: &b" + name, "", "&cFractured, must be repaired in an Ancient Altar");
        if (silkspawners) reinforcedSpawner = RepairedSilkSpawners.get(type);
        else reinforcedSpawner = REPAIRED_SPAWNER.getItem(RepairedSpawner.class).getItemForEntityType(type);
        new UnplaceableBlock(itemGroup, brokenSpawner, RecipeType.ANCIENT_ALTAR, new ItemStack[]{new ItemStack(Material.IRON_BARS), EARTH_RUNE, new ItemStack(Material.IRON_BARS), EARTH_RUNE, filledJarItem, EARTH_RUNE, new ItemStack(Material.IRON_BARS), EARTH_RUNE, new ItemStack(Material.IRON_BARS)}, BROKEN_SPAWNER.getItem(BrokenSpawner.class).getItemForEntityType(type)).register(addon);
        new SlimefunItem(itemGroup, new SlimefunItemStack(type + "_REINFORCED_SPAWNER", Material.SPAWNER, "&bReinforced Spawner", "&7Type: &b" + name), RecipeType.ANCIENT_ALTAR, new ItemStack[]{ENDER_RUNE, FILLED_FLASK_OF_KNOWLEDGE, ESSENCE_OF_AFTERLIFE, FILLED_FLASK_OF_KNOWLEDGE, brokenSpawner, FILLED_FLASK_OF_KNOWLEDGE, ESSENCE_OF_AFTERLIFE, FILLED_FLASK_OF_KNOWLEDGE, ENDER_RUNE}, reinforcedSpawner).register(addon);
        // @formatter:on
    }

    public Map<EntityType, Integer> getRequiredSouls() {
        return mobs;
    }
}