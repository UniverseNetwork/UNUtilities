package id.universenetwork.utilities.Bukkit.Manager;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import dev._2lstudios.hamsterapi.HamsterAPI;
import org.bukkit.Bukkit;

import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public class API {
    public static String nmsver;
    public static boolean useOldMethods = false;

    public static void ActionBarAPISetup() {
        System.out.println(prefix + " §6Preparing ActionBarAPI...");
        nmsver = Bukkit.getServer().getClass().getPackage().getName();
        nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
        if (nmsver.equalsIgnoreCase("v1_8_R1") || nmsver.startsWith("v1_7_")) useOldMethods = true;
        System.out.println(prefix + " §bActionBarAPI have been prepared");
    }

    public static void NoteBlockAPISetup(String Mode) {
        NoteBlockAPI API = new NoteBlockAPI();
        if (Mode.equalsIgnoreCase("enabling")) {
            System.out.println(prefix + " §6Preparing NoteBlockAPI...");
            API.onEnable();
            System.out.println(prefix + " §bNoteBlockAPI have been prepared");
        }
        if (Mode.equalsIgnoreCase("disabling")) {
            System.out.println(prefix + " §6Disabling NoteBlockAPI...");
            API.onDisable();
            System.out.println(prefix + " §bNoteBlockAPI has been disabled");
        }
    }

    public static void HamsterAPISetup(String Mode) {
        HamsterAPI API = new HamsterAPI();
        if (Mode.equalsIgnoreCase("enabling")) {
            System.out.println(prefix + " §6Preparing HamsterAPI...");
            API.onEnable();
            System.out.println(prefix + " §bHamsterAPI have been prepared");
        }
        if (Mode.equalsIgnoreCase("disabling")) {
            System.out.println(prefix + " §6Disabling HamsterAPI...");
            API.onDisable();
            System.out.println(prefix + " §bHamsterAPI has been disabled");
        }
    }
}