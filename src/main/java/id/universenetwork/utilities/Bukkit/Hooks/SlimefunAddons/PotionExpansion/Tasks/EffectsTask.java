package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Tasks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.EffectsManager;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.PotionSightEffect;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Utils.Color.Translate;

public class EffectsTask extends org.bukkit.scheduler.BukkitRunnable {
    public EffectsTask() {
        runTaskTimer(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, 0L, 20L);
    }

    @Override
    public void run() {
        for (Player player : org.bukkit.Bukkit.getOnlinePlayers()) {
            if (EffectsManager.hasAnyEffect(player)) {
                List<PotionSightEffect> effects = EffectsManager.getPlayerEffects(player);
                effects.forEach(effect -> effect.setTime(effect.getTime() - 1));
                showEffects(player, true);
                tick(player, effects);
                effects.removeIf(effect -> effect.getTime() <= 0);
                if (effects.size() == 0) EffectsManager.removePlayer(player.getUniqueId());
            }
        }
    }

    public void showEffects(@org.jetbrains.annotations.NotNull Player player, boolean actionBar) {
        StringBuilder message = new StringBuilder();
        message.append(Translate("&aEffects: &e"));
        boolean first = true;
        for (PotionSightEffect potionSightEffect : EffectsManager.getPlayerEffects(player)) {
            SimpleDateFormat formatter = new SimpleDateFormat("mm'm' ss's'");
            String formattedTime = formatter.format(new java.util.Date(potionSightEffect.getTime() * 1000L));
            if (first) {
                message.append(ChatUtils.humanize(potionSightEffect.getType().toString()) + " ");
                message.append(Translate("&aTime: &e" + formattedTime));
                first = false;
            } else {
                message.append(Translate("&a, &e" + ChatUtils.humanize(potionSightEffect.getType().toString()) + " "));
                message.append(Translate("&aTime: &e" + formattedTime));
            }
        }
        if (actionBar)
            player.spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(message.toString()));
        else player.sendMessage(message.toString());
    }

    void tick(Player player, List<PotionSightEffect> effects) {
        for (PotionSightEffect effect : effects) {
            id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.API.Effects.PotionSightType type = effect.getType();
            for (org.bukkit.Material ore : type.getOres())
                if (ore != null)
                    id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Utils.XRayUtil.showPathsToMaterial(player, ore, type.getColor(), id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.PotionExpansion.Settings.getSearchRadius());
        }
    }
}