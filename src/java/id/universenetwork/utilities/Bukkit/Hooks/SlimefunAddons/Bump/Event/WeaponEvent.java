package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Event;

import org.bukkit.Particle;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Bump.Sf.Weapon.*;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;

public class WeaponEvent implements org.bukkit.event.Listener {
    @EventHandler
    public void onRight(PlayerInteractEvent e) {
        try {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                final Player p = e.getPlayer();
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(soulSword_.getItemMeta().getDisplayName()) && e.getItem().getItemMeta().getLore().containsAll(soulSword_.getItemMeta().getLore())) {
                    int health = (int) p.getHealth();
                    int healths = (int) p.getHealthScale();
                    int foodLevel = p.getFoodLevel();
                    if (health == healths || foodLevel == 0)
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot activate this skill now!", false);
                    else if (foodLevel < 2)
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYour fullness is not enough to convert your health!", false);
                    else if (foodLevel > 2 && healths - health <= foodLevel) {
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oPart of your satiety has been converted into health!", false);
                        p.setFoodLevel(foodLevel - healths - health);
                        p.setHealth((health + healths - health));
                    } else if (healths - health > foodLevel) {
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYour fullness has been converted to health!", false);
                        p.setHealth((health + foodLevel));
                        p.setFoodLevel(0);
                    }
                }
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(devilSword_.getItemMeta().getDisplayName()) && e.getItem().getItemMeta().getLore().containsAll(devilSword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5)
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot activate this skill now!", false);
                    else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oKilling the devil, a knife is deadly!", false);
                        for (int i = 0; i < 20; i++) {
                            p.launchProjectile(SmallFireball.class);
                            Particle particle = Particle.ENCHANTMENT_TABLE;
                            p.spawnParticle(particle, p.getLocation(), 1);
                        }
                    }
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(skySword_.getItemMeta().getDisplayName()) && e.getItem().getItemMeta().getLore().containsAll(skySword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5)
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot activate this skill now!", false);
                    else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oBreak the sky, soar into the sky!", false);
                        Vector direction = p.getLocation().toVector();
                        p.setVelocity(p.getVelocity().add(direction.multiply(0.5D)));
                        for (int i = 0; i < 20; i++) {
                            Particle particle = Particle.EXPLOSION_HUGE;
                            p.spawnParticle(particle, p.getLocation(), 1);
                        }
                    }
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(skydevilSword_.getItemMeta().getDisplayName()) && e.getItem().getItemMeta().getLore().containsAll(skydevilSword_.getItemMeta().getLore()))
                    if (p.getFoodLevel() < 5)
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot activate this skill now!", false);
                    else {
                        p.setFoodLevel(p.getFoodLevel() - 5);
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oDestroy the devil and bring justice from the sky!", false);
                        p.setGlowing(true);
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 3));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 3));
                        (new BukkitRunnable() {
                            int i = 0;

                            @Override
                            public void run() {
                                if (this.i < 3) {
                                    p.launchProjectile(DragonFireball.class);
                                    this.i++;
                                } else {
                                    cancel();
                                    this.i = 0;
                                    p.setGlowing(false);
                                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oThe strengthening time is over!", false);
                                    p.removePotionEffect(PotionEffectType.SPEED);
                                }
                            }
                        }).runTaskTimer(plugin, 0L, 100L);
                    }
            }
        } catch (Exception ignore) {
        }
    }

    @EventHandler
    public void onRightBow(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(witherSkullRow_.getItemMeta().getDisplayName()) && e.getBow().getItemMeta().getLore().containsAll(witherSkullRow_.getItemMeta().getLore())) {
                e.setCancelled(true);
                if (p.getFoodLevel() < 5)
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot use it temporarily!", false);
                else {
                    p.setFoodLevel(p.getFoodLevel() - 5);
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oWithering!", false);
                    p.launchProjectile(WitherSkull.class);
                }
            }
            if (e.getBow().getItemMeta().getDisplayName().equalsIgnoreCase(LightBow_.getItemMeta().getDisplayName()) && e.getBow().getItemMeta().getLore().containsAll(LightBow_.getItemMeta().getLore())) {
                e.setCancelled(true);
                if (p.getFoodLevel() < 10)
                    getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oYou cannot use it temporarily!", false);
                else {
                    p.setFoodLevel(p.getFoodLevel() - 10);
                    for (int i = 0; i < 10; i++) {
                        getLocalization().sendActionbarMessage(p, "§b§k|§b- §7§oGod punishes!", false);
                        p.getWorld().strikeLightning(p.getTargetBlock(null, 200).getLocation());
                    }
                }
            }
        }
    }
}