package id.universenetwork.utilities.Bukkit.Manager;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import dev._2lstudios.hamsterapi.HamsterAPI;
import id.universenetwork.utilities.Bukkit.Utils.Logger;

public class API {
    public static void init() {
        Logger.info("&eInitializing API...");
        new HamsterAPI().onEnable();
        new NoteBlockAPI().onEnable();
        Logger.info("&aAll APIs has been initialized!");
    }

    public static void declare() {
        Logger.info("&eDeclaring API...");
        new HamsterAPI().onDisable();
        new NoteBlockAPI().onDisable();
        Logger.info("&cAll APIs has been declared!");
    }
}