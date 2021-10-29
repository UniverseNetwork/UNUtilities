package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.*;
import id.universenetwork.utilities.Bukkit.Enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Manager.Config.VOEnabled;
import static id.universenetwork.utilities.Bukkit.UNUtilities.prefix;

public abstract class Commands extends org.bukkit.command.Command {
    final String Permission;
    final String Description;
    final boolean PlayerOnly;
    final String[] Aliases;

    public Commands(String Name, String Description, String Permission, boolean PlayerOnly, String... Aliases) {
        super(Name);
        this.Permission = Permission;
        this.Description = Description;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = Aliases;
        registerCommands();
    }

    public Commands(String Name, String Description, String Permission, boolean PlayerOnly) {
        super(Name);
        this.Description = Description;
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = null;
        registerCommands();
    }

    public Commands(String Name, String Description, boolean PlayerOnly) {
        super(Name);
        this.Description = Description;
        this.Permission = null;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = null;
        registerCommands();
    }

    public Commands(String Name, String Description, boolean PlayerOnly, String... Aliases) {
        super(Name);
        this.Description = Description;
        this.Permission = null;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = Aliases;
        registerCommands();
    }

    public Commands(String Name, boolean PlayerOnly) {
        super(Name);
        this.Description = null;
        this.Permission = null;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = null;
        registerCommands();
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

    public abstract void Execute(CommandSender sender, String[] args);

    public abstract List<String> TabComplete(CommandSender sender, String str, String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (Permission != null && !sender.hasPermission(Permission)) {
            sender.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return true;
        }
        if (PlayerOnly && !(sender instanceof Player)) {
            sender.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return true;
        }
        Execute(sender, args);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (Permission != null && !sender.hasPermission(Permission)) return Collections.emptyList();
        return TabComplete(sender, alias, args);
    }

    @Override
    public List<String> getAliases() {
        if (Aliases == null) return Collections.emptyList();
        List<String> alias = new java.util.ArrayList<>();
        Collections.addAll(alias, Aliases);
        return alias;
    }

    @Override
    public String getDescription() {
        if (Description == null) return "";
        return Description;
    }

    public static void register() {
        System.out.println(prefix + " §eRegistering Commands...");
        new UNU();
        new Hat();
        new ChangeSlots();
        // new Keep();
        new AAVLP(VOEnabled());
        new VLP(VOEnabled());
        new LimitPillagers();
        System.out.println(prefix + " §aAll Commands Successfully Registered");
    }
}