package id.universenetwork.utilities.Bukkit.Manager;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import dev._2lstudios.hamsterapi.HamsterAPI;

import static id.universenetwork.utilities.Bukkit.Utils.Logger.info;

public class API {
    public static void init() {
        info("&eInitializing API...");
        new HamsterAPI().onEnable();
        new NoteBlockAPI().onEnable();
        info("&aAll APIs has been initialized!");
    }

    public static void declare() {
        info("&eDeclaring API...");
        new HamsterAPI().onDisable();
        new NoteBlockAPI().onDisable();
        info("&cAll APIs has been declared!");
    }
}