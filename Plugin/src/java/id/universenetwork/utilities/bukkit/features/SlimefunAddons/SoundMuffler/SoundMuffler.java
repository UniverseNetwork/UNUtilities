package id.universenetwork.utilities.bukkit.features.SlimefunAddons.SoundMuffler;

import id.universenetwork.utilities.bukkit.annotations.Dependency;
import id.universenetwork.utilities.bukkit.features.SlimefunAddons.SfAddon;
import id.universenetwork.utilities.bukkit.UNUtilities;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;

@Dependency({"ProtocolLib"})
public class SoundMuffler extends SfAddon {
    public static ItemGroup SOUND_MUFFLER;

    @Override
    public void Load() {
        SOUND_MUFFLER = new ItemGroup(UNUtilities.createKey("sound_muffler"),
                new CustomItemStack(Material.BEACON, "&7SoundMuffler", "", "&a> Click to open"));
        new SoundMufflerListener();
        SoundMufflerMachine soundMufflerMachine = new SoundMufflerMachine();
        soundMufflerMachine.register(this);
        new Research(UNUtilities.createKey("sound_muffler"),
                6912, "Sound Muffler", 11).addItems(soundMufflerMachine.getItem()).register();
    }
}