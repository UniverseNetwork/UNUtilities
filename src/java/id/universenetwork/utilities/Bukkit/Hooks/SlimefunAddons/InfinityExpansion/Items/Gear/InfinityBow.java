package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear;

import io.github.thebusybiscuit.slimefun4.core.handlers.BowShootHandler;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class InfinityBow extends io.github.thebusybiscuit.slimefun4.implementation.items.weapons.ExplosiveBow {
    @javax.annotation.ParametersAreNonnullByDefault
    public InfinityBow(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType recipeType, org.bukkit.inventory.ItemStack[] recipe) {
        super(itemGroup, item, recipe);
        setRecipeType(recipeType);
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public BowShootHandler onShoot() {
        BowShootHandler explosive = super.onShoot();
        return (e, target) -> {
            explosive.onHit(e, target);
            if (target instanceof Player) {
                Player p = (Player) target;
                // Fixes #3060 - Don't apply effects if the arrow was successfully blocked.
                if (p.isBlocking() && e.getFinalDamage() <= 0) return;
                if (io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getMinecraftVersion().isAtLeast(io.github.thebusybiscuit.slimefun4.api.MinecraftVersion.MINECRAFT_1_17))
                    p.setFreezeTicks(60);
            }
            target.getWorld().playEffect(target.getLocation(), Effect.STEP_SOUND, Material.ICE);
            target.getWorld().playEffect(target.getEyeLocation(), Effect.STEP_SOUND, Material.ICE);
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 2, 10));
            target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 2, -10));
        };
    }
}