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
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.GOLD_24K;
import static org.bukkit.Material.*;

public class Tools {
    public static SlimefunItemStack appraisalPaperDamage_;
    public static SlimefunItemStack appraisalPaperArmor_;
    public static SlimefunItemStack getGoldSpade_ = new SfItemStackCreate("GETGOLD_SPADE", GOLDEN_SHOVEL, "&eGold Shovel", new String[]{"", "&b&k|&b- There is a certain chance to dig out broken gold coins in the sand!", ""}, "MENDING-3");
    public ItemGroup tools;

    static {
        appraisalPaperArmor_ = new SfItemStackCreate("APPRAISAL_PAPER_ARMOR", PAPER, "&eArmor Quality Identifier", new String[]{"", "&b&k|&b- A random armor attribute for a given item!", "&b&k|&b- Very expensive, be sure to keep it...", ""});
        appraisalPaperDamage_ = new SfItemStackCreate("APPRAISAL_PAPER_DAMAGE", PAPER, "&eWeapon Quality Identifier", new String[]{"", "&b&k|&b- A random attack attribute for a given item!", "&b&k|&b- Very expensive, be sure to keep it...", ""});
    }

    public Tools() {
        tools = new ItemGroup(new NamespacedKey(plugin, "Tools"), new CustomItemStack(DIAMOND_PICKAXE, "&bBump-Tool", "", "&b&k|&b- Click to open >", "", "&7Tools, tools!", "They may contain unknown power..."));
        tools.register(addon);
        SlimefunItem appraisalPaperArmor = new SlimefunItem(tools, appraisalPaperArmor_, RecipeType.SMELTERY, new ItemStack[]{Armor.randomHelmet_});
        SlimefunItem appraisalPaperDamage = new SlimefunItem(tools, appraisalPaperDamage_, RecipeType.SMELTERY, new ItemStack[]{Weapon.randomSword_});
        SlimefunItem getgoldSpade = new SlimefunItem(tools, getGoldSpade_, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{null, GOLD_24K, new ItemStack(STICK), new ItemStack(STICK)});
        new Register(appraisalPaperArmor, appraisalPaperDamage, getgoldSpade);
    }
}