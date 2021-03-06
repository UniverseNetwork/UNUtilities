package id.universenetwork.utilities.bukkit.Filters;

public enum FilterAction {
    // Represents the creation of a book, either via ingame mechanics or creative inventory actions
    CREATE,

    // Represents the reading of a book, e.g. by holding it or on a lectern
    READ,

    // Explicitly filter by invoking the command
    COMMAND
}