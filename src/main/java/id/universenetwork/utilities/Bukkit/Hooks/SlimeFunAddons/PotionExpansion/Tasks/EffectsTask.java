package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Tasks;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.EffectsManager;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.PotionSightEffect;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects.PotionSightType;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Settings;
import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Utils.XRayUtil;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Color.Translator;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class EffectsTask extends BukkitRunnable {
    public EffectsTask() {
        runTaskTimer(plugin, 0L, 20L);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
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

    public void showEffects(@Nonnull Player player, boolean actionBar) {
        StringBuilder message = new StringBuilder();
        message.append(Translator("&aEffects: &e"));
        boolean first = true;
        for (PotionSightEffect potionSightEffect : EffectsManager.getPlayerEffects(player)) {
            SimpleDateFormat formatter = new SimpleDateFormat("mm'm' ss's'");
            String formattedTime = formatter.format(new Date(potionSightEffect.getTime() * 1000L));
            if (first) {
                message.append(ChatUtils.humanize(potionSightEffect.getType().toString()) + " ");
                message.append(Translator("&aTime: &e" + formattedTime));
                first = false;
            } else {
                message.append(Translator("&a, &e" + ChatUtils.humanize(potionSightEffect.getType().toString()) + " "));
                message.append(Translator("&aTime: &e" + formattedTime));
            }
        }
        if (actionBar) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message.toString()));
        } else player.sendMessage(message.toString());
    }

    @ParametersAreNonnullByDefault
    void tick(Player player, List<PotionSightEffect> effects) {
        for (PotionSightEffect effect : effects) {
            PotionSightType type = effect.getType();
            for (Material ore : type.getOres())
                if (ore != null) XRayUtil.showPathsToMaterial(player, ore, type.getColor(), Settings.getSearchRadius());
        }
    }
}