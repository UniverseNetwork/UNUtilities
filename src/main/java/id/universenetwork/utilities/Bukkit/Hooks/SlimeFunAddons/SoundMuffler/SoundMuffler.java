package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.SoundMuffler;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.Addons.addon;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class SoundMuffler {
    public static ItemGroup SOUND_MUFFLER;

    public SoundMuffler() {
        if (Enabled("SoundMuffler")) if (getPluginManager().isPluginEnabled("ProtocolLib")) {
            SOUND_MUFFLER = new ItemGroup(new NamespacedKey(plugin, "sound_muffler"), new CustomItemStack(Material.BEACON, "&7SoundMuffler", "", "&a> Click to open"));
            new SoundMufflerListener().start();
            SoundMufflerMachine soundMufflerMachine = new SoundMufflerMachine();
            soundMufflerMachine.register(addon);
            new Research(new NamespacedKey(plugin, "sound_muffler"), 6912, "Sound Muffler", 11).addItems(soundMufflerMachine.getItem()).register();
            getLogger().info(prefix + " §aProtocolLib found. §bSuccessfully Registered §dSoundMuffler §bAddon");
        } else
            getLogger().warning(prefix + " §eProtocolLib not found. §cYou need ProtocolLib to use §dSoundMuffler §cAddon");
    }
}