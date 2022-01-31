package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.DynaTech.Items.Misc;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class StarDustMeteor extends io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock implements io.github.thebusybiscuit.slimefun4.api.geo.GEOResource {
    public static final SlimefunItemStack STARDUST_METEOR = new SlimefunItemStack("STARDUST_METEOR", io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead.getItemStack(io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin.fromHashCode("c482d1ba4bdac990f6ea987703587fd79fe55555363251984679d4f279cc0c2a")), "&6Stardust Meteor", "", "&fGeomined from Mountain or Badlands Biomes");
    final NamespacedKey key = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "stardust_meteor");

    public StarDustMeteor(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup) {
        super(itemGroup, STARDUST_METEOR, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.GEO_MINER, new ItemStack[0]);
        register();
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    @NotNull
    @Override
    public ItemStack getItem() {
        return STARDUST_METEOR.clone();
    }

    @NotNull
    @Override
    public String getName() {
        return "Stardust Meteor";
    }

    @Override
    public boolean isObtainableFromGEOMiner() {
        return true;
    }

    @Override
    public int getDefaultSupply(@NotNull org.bukkit.World.Environment environment, @NotNull Biome biome) {
        if (biome == Biome.MOUNTAINS || biome == Biome.BADLANDS) return 16;
        else return 0;
    }

    @Override
    public int getMaxDeviation() {
        return 4;
    }
}