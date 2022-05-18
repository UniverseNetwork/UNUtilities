package id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SoundMuffler;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;

import static id.universenetwork.utilities.Bukkit.UNUtilities.createKey;

@id.universenetwork.utilities.Bukkit.Annotations.Dependency({"ProtocolLib"})
public class SoundMuffler extends id.universenetwork.utilities.Bukkit.Features.SlimefunAddons.SfAddon {
    public static ItemGroup SOUND_MUFFLER;

    @Override
    public void Load() {
        SOUND_MUFFLER = new ItemGroup(createKey("sound_muffler"), new io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack(org.bukkit.Material.BEACON, "&7SoundMuffler", "", "&a> Click to open"));
        new SoundMufflerListener();
        SoundMufflerMachine soundMufflerMachine = new SoundMufflerMachine();
        soundMufflerMachine.register(this);
        new io.github.thebusybiscuit.slimefun4.api.researches.Research(createKey("sound_muffler"), 6912, "Sound Muffler", 11).addItems(soundMufflerMachine.getItem()).register();
    }
}