package id.universenetwork.utilities.bukkit.Hooks.SkriptAddons.Skream.Utils;

import java.util.ArrayList;

public class ServerIdeas {
    public static String getRandom() {
        ArrayList<String> ideas = new ArrayList<>();
        ideas.add("KitPVP");
        ideas.add("Minigames");
        ideas.add("TNTRun");
        ideas.add("BedWars");
        ideas.add("CakeWars");
        ideas.add("Skyblock");
        ideas.add("Spleef");
        ideas.add("LaserTag");
        ideas.add("KingofTheHill");
        ideas.add("Creative");
        ideas.add("SMP");
        ideas.add("Prison");
        ideas.add("Racing");
        ideas.add("Olympics");
        ideas.add("Dungeon");
        ideas.add("Sumo");
        ideas.add("ThemePark");
        ideas.add("Roleplay");
        ideas.add("Duels");
        ideas.add("Factions");
        ideas.add("Skywars");
        ideas.add("Hardcore");
        ideas.add("Parkour");
        ideas.add("Adventure/Exploration");
        ideas.add("UHC");
        return ideas.get(new java.util.Random().nextInt(ideas.size()));
    }
}