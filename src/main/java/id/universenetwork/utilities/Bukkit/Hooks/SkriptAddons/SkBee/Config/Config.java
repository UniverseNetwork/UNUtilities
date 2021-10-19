package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkBee.Config;

import org.bukkit.configuration.ConfigurationSection;

import static id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.Addons.Settings;

public class Config {
    // Config stuff
    public final boolean SETTINGS_DEBUG;
    public final boolean ELEMENTS_NBT;
    public final boolean ELEMENTS_BOARD;
    public final boolean ELEMENTS_RECIPE;
    public final boolean ELEMENTS_BOUND;
    public final boolean ELEMENTS_STRUCTURE;
    public final boolean ELEMENTS_VIRTUAL_FURNACE;
    public final boolean ELEMENTS_TEXT_COMPONENT;
    public final boolean ELEMENTS_PATHFINDING;
    public final boolean ELEMENTS_WORLD_CREATOR;
    public final boolean AUTO_LOAD_WORLDS;
    public final String RECIPE_NAMESPACE;

    public Config() {
        ConfigurationSection config = Settings("SkBee");
        SETTINGS_DEBUG = config.getBoolean("settings.debug");
        ELEMENTS_NBT = config.getBoolean("elements.nbt");
        ELEMENTS_BOARD = config.getBoolean("elements.scoreboard");
        ELEMENTS_RECIPE = config.getBoolean("elements.recipe");
        ELEMENTS_BOUND = config.getBoolean("elements.bound");
        ELEMENTS_STRUCTURE = config.getBoolean("elements.structure");
        ELEMENTS_VIRTUAL_FURNACE = config.getBoolean("elements.virtual-furnace");
        ELEMENTS_TEXT_COMPONENT = config.getBoolean("elements.text-component");
        ELEMENTS_PATHFINDING = config.getBoolean("elements.pathfinding");
        ELEMENTS_WORLD_CREATOR = config.getBoolean("elements.world-creator");
        AUTO_LOAD_WORLDS = config.getBoolean("elements.auto-load-custom-worlds");
        String namespace = config.getString("recipe.namespace");
        if (namespace == null) namespace = "skrecipe";
        RECIPE_NAMESPACE = namespace.toLowerCase();
    }
}
