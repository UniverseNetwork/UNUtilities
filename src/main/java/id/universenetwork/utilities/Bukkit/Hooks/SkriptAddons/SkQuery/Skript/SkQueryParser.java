package id.universenetwork.utilities.Bukkit.Hooks.SkriptAddons.SkQuery.Skript;

import ch.njol.skript.classes.Parser;

public abstract class SkQueryParser<T> extends Parser<T> {
    final String variableNamePattern;

    public SkQueryParser(String variableNamePattern) {
        this.variableNamePattern = variableNamePattern;
    }

    @Override
    public String getVariableNamePattern() {
        return this.variableNamePattern + ":.+";
    }

    @Override
    public String toVariableNameString(T object) {
        return this.variableNamePattern + ":" + " " + object.toString().toLowerCase();
    }

    @Override
    public String toString(T object, int i) {
        return object.toString().toLowerCase().replace("_", " ");
    }
}