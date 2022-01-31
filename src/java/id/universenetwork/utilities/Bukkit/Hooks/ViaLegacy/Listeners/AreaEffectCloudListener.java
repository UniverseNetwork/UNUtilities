package id.universenetwork.utilities.Bukkit.Hooks.ViaLegacy.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Particle;

import java.util.ArrayList;

public class AreaEffectCloudListener implements org.bukkit.event.Listener {
    final ArrayList<org.bukkit.entity.AreaEffectCloud> effectClouds = new ArrayList<>();

    public AreaEffectCloudListener() {
        Bukkit.getScheduler().runTaskTimer(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, () -> {
            java.util.Set<org.bukkit.entity.Player> players = Bukkit.getOnlinePlayers().stream().filter(p -> com.viaversion.viaversion.api.Via.getAPI().getPlayerVersion(p) <= 54).collect(java.util.stream.Collectors.toSet());
            effectClouds.removeIf(e -> !e.isValid());
            effectClouds.forEach(cloud -> {
                org.bukkit.World world = cloud.getWorld();
                Particle particle = cloud.getParticle();
                org.bukkit.Location loc = cloud.getLocation();
                float radius = cloud.getRadius();
                float area = (float) Math.PI * radius * radius;
                for (int i = 0; i < area; i++) {
                    float f1 = (float) Math.random() * 6.2831855F;
                    float f2 = (float) Math.sqrt(Math.random()) * radius;
                    float f3 = (float) Math.cos(f1) * f2;
                    float f6 = (float) Math.sin(f1) * f2;
                    if (particle == Particle.SPELL_MOB) {
                        int color = cloud.getColor().asRGB();
                        int r = color >> 16 & 255;
                        int g = color >> 8 & 255;
                        int b = color & 255;
                        players.forEach(p -> {
                            if (p.getWorld() != loc.getWorld()) return;
                            p.spawnParticle(particle, loc.getX() + f3, loc.getY(), loc.getZ() + f6, 0, r / 255f, g / 255f, b / 255f);
                        });
                    } // TODO particles with data
                    // world.spawnParticle(particle, loc.getX() + f3, loc.getY(), loc.getZ() + f6, 1, (0.5 - Math.random()) * 0.15, 0.009999999776482582D, (0.5 - Math.random()) * 0.15d, );
                }
            });
        }, 1L, 1L);
    }

    @org.bukkit.event.EventHandler
    public void onEntitySpawn(org.bukkit.event.entity.LingeringPotionSplashEvent e) {
        effectClouds.add(e.getAreaEffectCloud());
    }
}