package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.Commands.GSetSlots;
import id.universenetwork.utilities.Bungee.Commands.StaffList;
import id.universenetwork.utilities.Bungee.Commands.UniverseUtilitiesBungee;
import id.universenetwork.utilities.Universal.Enums.Settings;
import net.md_5.bungee.api.CommandSender;

import static id.universenetwork.utilities.Bungee.UNUtilities.plugin;
import static id.universenetwork.utilities.Bungee.UNUtilities.prefix;

public abstract class Commands extends net.md_5.bungee.api.plugin.Command implements net.md_5.bungee.api.plugin.TabExecutor {
    final String Permission;
    final boolean PlayerOnly;
    final String[] Aliases;

    public Commands(String Name, String Permission, boolean PlayerOnly, String... Aliases) {
        super(Name);
        this.Permission = Permission;
        this.PlayerOnly = PlayerOnly;
        this.Aliases = Aliases;
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    public Commands(String Name, boolean PlayerOnly, String... Aliases) {
        this(Name, null, PlayerOnly, Aliases);
    }

    public Commands(String Name, boolean PlayerOnly) {
        this(Name, PlayerOnly, (String) null);
    }

    public Commands(String Name, String Permission, boolean PlayerOnly) {
        this(Name, Permission, PlayerOnly, (String) null);
    }

    public abstract void Execute(CommandSender Sender, String[] Args);

    public abstract Iterable<String> TabComplete(CommandSender Sender, String[] Args);

    @Override
    public String[] getAliases() {
        return Aliases;
    }

    @Override
    public void execute(CommandSender s, String[] a) {
        if (Permission != null && !s.hasPermission(Permission)) {
            s.sendMessage(Config.Settings(Settings.NOPERMISSION));
            return;
        }
        if (PlayerOnly && !(s instanceof net.md_5.bungee.api.connection.ProxiedPlayer)) {
            s.sendMessage(Config.Settings(Settings.DENYCONSOLE));
            return;
        }
        Execute(s, a);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender s, String[] a) {
        if (Permission != null && !s.hasPermission(Permission)) return java.util.Collections.emptyList();
        return TabComplete(s, a);
    }

    public static void Register() {
        System.out.println(prefix + " §eRegistering Commands...");
        new UniverseUtilitiesBungee();
        new GSetSlots();
        new StaffList();
        System.out.println(prefix + " §aAll Commands Successfully Registered");
    }
}