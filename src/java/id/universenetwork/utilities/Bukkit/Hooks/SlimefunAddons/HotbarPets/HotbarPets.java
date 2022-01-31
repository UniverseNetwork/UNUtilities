package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Groups.*;
import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.Listeners.*;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.Addons.Enabled;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.HotbarPets.PetTexture.ITEM_GROUP;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;
import static org.bukkit.Bukkit.getScheduler;

public class HotbarPets {
    ItemGroup itemGroup;

    public HotbarPets() {
        if (Enabled("HotbarPets")) {
            itemGroup = new ItemGroup(new NamespacedKey(plugin, "pets"), new CustomItemStack(ITEM_GROUP.getAsItem(), "&dHotbar Pets", "", "&a> Click to open"));

            // Add all the Pets via their Group class
            new FarmAnimals(this);
            new PeacefulAnimals(this);
            new PassiveMobs(this);
            new HostileMobs(this);
            new BossMobs(this);
            new UtilityPets(this);
            new SpecialPets(this);

            // Registering the Listeners
            new DamageListener();
            new FoodListener();
            new GeneralListener();
            new PhantomListener();
            new ProjectileListener();
            new SoulPieListener();
            new TNTListener();

            // Registering our task
            getScheduler().scheduleSyncRepeatingTask(plugin, new HotbarPetsRunnable(), 0L, 2000L);
            System.out.println(prefix + " §bSuccessfully Registered §dHotbarPets §bAddon");
        }
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }
}
