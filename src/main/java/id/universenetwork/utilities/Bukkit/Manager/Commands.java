package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.VOEnabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public abstract class Commands extends Command {
    final String Permission;
    final boolean PlayerOnly;

    public Commands(String Name, String Permission, boolean PlayerOnly) {
        super(Name);
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
    }

    public Commands(String Name, String Description, String Permission, boolean PlayerOnly) {
        super(Name);
        setDescription(Description);
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
    }

    public Commands(String Name, List<String> Aliases, String Description, String Permission, boolean PlayerOnly) {
        super(Name);
        setAliases(Aliases);
        setDescription(Description);
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
    }

    public Commands(String Name, List<String> Aliases, String Permission, boolean PlayerOnly) {
        super(Name);
        setAliases(Aliases);
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
    }

    void registerCommands() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(getLabel(), this);
        } catch (Exception e) {
            Bukkit.getLogger().severe(prefix + " §cFailed to register command! [" + getName() + "]");
            e.printStackTrace();
        }
    }

    public abstract void Execute(CommandSender sender, Command command, String[] args);

    public abstract List<String> TabComplete(CommandSender sender, Command command, String str, String[] args);

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission(Permission)) {
            sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return true;
        }
        if (PlayerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return true;
        }
        Execute(sender, this, args);
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (!sender.hasPermission(Permission)) sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
        if (PlayerOnly && !(sender instanceof Player)) sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
        return TabComplete(sender, this, alias, args);
    }

    public static void register() {
        System.out.println(prefix + " §eRegistering Commands...");
        new UNU();
        new Hat();
        new ChangeSlots();
        // new Keep();
        new AAVLP(VOEnabled());
        new VLP(VOEnabled());
        System.out.println(prefix + " §aAll Commands Successfully Registered");
    }
}