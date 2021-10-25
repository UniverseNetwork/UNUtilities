package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.Register;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Util.SfItemStackCreate;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;
import static org.bukkit.Material.*;

public class Weapon {
    public static SlimefunItemStack guardSword_;
    public static SlimefunItemStack peachSword_;
    public static SlimefunItemStack soulSword_;
    public static SlimefunItemStack skySword_;
    public static SlimefunItemStack devilSword_;
    public static SlimefunItemStack skydevilSword_;
    public static SlimefunItemStack emerSword_;
    public static SlimefunItemStack boneSword_;
    public static SlimefunItemStack randomSword_;
    public static SlimefunItemStack witherSkullRow_;
    public static SlimefunItemStack LightBow_ = new SfItemStackCreate("LIGHT_BOW", BOW, "&6God's Punishment Bow", new String[]{"", "&b&k|&b- &7&oAccept God's punishment...", ""}, "ARROW_DAMAGE-5");
    public ItemGroup weapon;

    static {
        witherSkullRow_ = new SfItemStackCreate("WITHERSKULL_ROW", BOW, "&6Withered Bow", new String[]{"", "&b&k|&b- &7&oWhat it launches is every undead...", ""}, "ARROW_DAMAGE-5");
        emerSword_ = new SfItemStackCreate("EMER_SWORD", DIAMOND_SWORD, "&6Emerald Sword", new String[]{"", "&b&k|&b- &7&oMade of expensive emeralds...", ""}, "DAMAGE_ALL-1");
        boneSword_ = new SfItemStackCreate("BONE_SWORD", WOODEN_SWORD, "&6Bone Sword", new String[]{"", "&b&k|&b- &7&oVery hard...", ""}, "DURABILITY-10");
        randomSword_ = new SfItemStackCreate("RANDOM_SWORD", DIAMOND_SWORD, "&6Random Short Edge", new String[]{"", "&b&k|&b- &7&oHow is my luck...", "&b&k|&b- &e&oThe appraiser &7&orandomly gives this attribute...", "§b§k|§b- §7§oThe weapon has not yet been identified..."});
        guardSword_ = new SfItemStackCreate("GUARD_SWORD", GOLDEN_SWORD, "&6Guardian Sword", new String[]{"", "&b&k|&b- &7&oI will guard you until the last moment of life...", ""}, "DAMAGE_ALL-5", "IMPALING-3");
        peachSword_ = new SfItemStackCreate("PEACH_SWORD", WOODEN_SWORD, "&6Peach Wood Sword", new String[]{"", "&b&k|&b- &7&oTribute to the classic zombie movie...", ""}, "KNOCKBACK-5", "DURABILITY-3");
        soulSword_ = new SfItemStackCreate("SOUL_SWORD", IRON_SWORD, "&6Soul Sword", new String[]{"", "&b&k|&b- &7&oThe soul of the devil is injected here...", "&b&k|&b- &e&oRight click &7&oto make a low growl...", "&b&k|&b- &7&oTurn &7&oyour &e&osatiety &7&ointo &e&ohealth&7&o...", ""}, "DAMAGE_ALL-2", "DURABILITY-1");
        skySword_ = new SfItemStackCreate("SKY_SWORD", GOLDEN_SWORD, "&6Heaven Breaking Sword", new String[]{"", "&b&k|&b- &7&oPo Tian, what a domineering name...", "&b&k|&b- &e&oRight click &7&oand rush to the sky...", ""}, "DAMAGE_ALL-1", "DURABILITY-1", "SWEEPING_EDGE-1");
        devilSword_ = new SfItemStackCreate("DEVIL_SWORD", IRON_SWORD, "&6Demon Sword", new String[]{"", "&b&k|&b- &7&oDemon Slayer, what a mighty title...", "&b&k|&b- &e&oRight click &7&oto send out the holy fire to sweep all darkness...", ""}, "DAMAGE_ALL-1", "DURABILITY-1", "SWEEPING_EDGE-1");
        skydevilSword_ = new SfItemStackCreate("SKY_DEVIL_SWORD", DIAMOND_SWORD, "&6Devil Sword", new String[]{"", "&b&k|&b- &7&oBreaking the sky and cutting the devil, one after another emits powerful energy...", "&b&k|&b- &e&oRight-click &7&oto launch three breaths forward in a row and elevate yourself..."}, "DAMAGE_ALL-5", "DURABILITY-5", "LOYALTY-5");
    }

    public Weapon() {
        weapon = new ItemGroup(new NamespacedKey(plugin, "Weapon"), new CustomItemStack(IRON_SWORD, "&aBump-Magic Weapon", "", "&b&k|&b- Click to open >", "", "&7These things are more powerful by magic!", "Please study them carefully!"));
        weapon.register(addon);
        SlimefunItem LightBow = new SlimefunItem(weapon, LightBow_, RecipeType.ARMOR_FORGE, new ItemStack[]{LIGHTNING_RUNE, STAFF_STORM, LIGHTNING_RUNE, POWER_CRYSTAL, STAFF_STORM, LIGHTNING_RUNE, STAFF_STORM});
        SlimefunItem witherskullbow = new SlimefunItem(weapon, witherSkullRow_, RecipeType.ARMOR_FORGE, new ItemStack[]{NECROTIC_SKULL, Stuff.peachwood_, NECROTIC_SKULL, POWER_CRYSTAL, Stuff.peachwood_, NECROTIC_SKULL, Stuff.peachwood_});
        SlimefunItem randomSword = new SlimefunItem(weapon, randomSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, null, null, null, Stuff.make_, new ItemStack(STICK)});
        SlimefunItem boneSword = new SlimefunItem(weapon, boneSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(BONE_BLOCK, 64), new ItemStack(BONE_BLOCK, 64), GRANDMAS_WALKING_STICK});
        SlimefunItem emerSword = new SlimefunItem(weapon, emerSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, new ItemStack(EMERALD), new ItemStack(EMERALD), new ItemStack(STICK)});
        SlimefunItem peachSword = new SlimefunItem(weapon, peachSword_, RecipeType.ARMOR_FORGE, new ItemStack[]{null, Stuff.peachwood_, Stuff.peachwood_, new ItemStack(STICK)});
        SlimefunItem skydevilSword = new SlimefunItem(weapon, skydevilSword_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, skySword_, Stuff.upDatePower_, devilSword_});
        SlimefunItem devilSword = new SlimefunItem(weapon, devilSword_, RecipeType.ARMOR_FORGE, new ItemStack[]{MAGIC_LUMP_2, ENDER_RUNE, MAGIC_LUMP_2, FIRE_RUNE, FIRE_RUNE, MAGIC_LUMP_2, ENDER_RUNE, MAGIC_LUMP_2});
        SlimefunItem skySword = new SlimefunItem(weapon, skySword_, RecipeType.ARMOR_FORGE, new ItemStack[]{MAGIC_LUMP_2, AIR_RUNE, MAGIC_LUMP_2, RAINBOW_RUNE, RAINBOW_RUNE, MAGIC_LUMP_2, AIR_RUNE, MAGIC_LUMP_2});
        SlimefunItem guardSword = new SlimefunItem(weapon, guardSword_, RecipeType.ARMOR_FORGE, new ItemStack[]{null, Stuff.sunEnergy_, Stuff.sunEnergy_, new ItemStack(STICK)});
        SlimefunItem soulSword = new SlimefunItem(weapon, soulSword_, RecipeType.ARMOR_FORGE, new ItemStack[]{null, Stuff.soulPaper_, Stuff.soulPaper_, new ItemStack(DIAMOND_SWORD)});
        new Register(LightBow, witherskullbow, randomSword, boneSword, emerSword, peachSword, skydevilSword, devilSword, skySword, guardSword, soulSword);
    }
}