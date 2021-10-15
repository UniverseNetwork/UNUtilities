package id.universenetwork.utilities.Bukkit.Manager;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
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
        if (Mode.equalsIgnoreCase("enabling")) {
            System.out.println(prefix + " §6Preparing NoteBlockAPI...");
            NoteBlockAPI.getAPI().onEnable();
            System.out.println(prefix + " §bNoteBlockAPI have been prepared");
        }
        if (Mode.equalsIgnoreCase("disabling")) {
            System.out.println(prefix + " §6Disabling NoteBlockAPI...");
            NoteBlockAPI.getAPI().onDisable();
            System.out.println(prefix + " §bNoteBlockAPI has been disabled");
        }
    }
}