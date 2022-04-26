package id.universenetwork.utilities.Bukkit.Manager;

import id.universenetwork.utilities.Bukkit.Commands.*;
import id.universenetwork.utilities.Universal.Enums.Settings;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

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
        try {
            java.lang.reflect.Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(getLabel(), this);
        } catch (Exception e) {
            Bukkit.getLogger().severe(prefix + " §cFailed to register command! [" + getName() + "]");
            e.printStackTrace();
        }
    }

    public Commands(String Name, String Description, String Permission, boolean PlayerOnly) {
        this(Name, Description, Permission, PlayerOnly, null);
    }

    public Commands(String Name, String Description, boolean PlayerOnly) {
        this(Name, Description, null, PlayerOnly);
    }

    public Commands(String Name, String Description, boolean PlayerOnly, String... Aliases) {
        this(Name, Description, null, PlayerOnly, Aliases);
    }

    public Commands(String Name, boolean PlayerOnly) {
        this(Name, null, PlayerOnly);
    }

    public abstract void Execute(CommandSender sender, String[] args);

    public abstract List<String> TabComplete(CommandSender sender, String str, String[] args);

    @Override
    public boolean execute(CommandSender s, String commandLabel, String[] a) {
        if (Permission != null && !s.hasPermission(Permission)) {
            s.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return true;
        }
        if (PlayerOnly && !(s instanceof org.bukkit.entity.Player)) {
            s.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return true;
        }
        Execute(s, a);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender s, String b, String[] a) throws IllegalArgumentException {
        if (Permission != null && !s.hasPermission(Permission)) return Collections.emptyList();
        return TabComplete(s, b, a);
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

    public static void Register() {
        System.out.println(prefix + " §eRegistering Commands...");
        new UniverseUtilities();
        new Hat();
        new SetSlots();
        new AAVLP(VOEnabled());
        new VLP(VOEnabled());
        new LimitPillagers();
        System.out.println(prefix + " §aAll Commands Successfully Registered");
    }
}