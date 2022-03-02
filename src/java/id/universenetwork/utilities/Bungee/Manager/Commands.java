package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.Commands.GSetSlots;
import id.universenetwork.utilities.Bungee.Commands.StaffList;
import id.universenetwork.utilities.Bungee.Commands.UniverseUtilitiesBungee;
import id.universenetwork.utilities.Bungee.Commands.WhitelistBungee;
import id.universenetwork.utilities.Bungee.UNUtilities;
import id.universenetwork.utilities.Universal.Annotations.CommandProperties;
import net.md_5.bungee.api.CommandSender;

public abstract class Commands {
    final CommandProperties p;

    public Commands() {
        p = java.util.Objects.requireNonNull(getClass().getDeclaredAnnotation(CommandProperties.class), "Commands must be have CommandProperties Annotation");
        UNUtilities.plugin.getProxy().getPluginManager().registerCommand(UNUtilities.plugin, new Build(p.Name()));
    }

    public static void Register() {
        System.out.println(UNUtilities.prefix + " §eRegistering Commands...");
        new UniverseUtilitiesBungee();
        new GSetSlots();
        new StaffList();
        new WhitelistBungee();
        System.out.println(UNUtilities.prefix + " §aAll Commands Successfully Registered");
    }

    public abstract void Execute(CommandSender Sender, String[] Args);

    public abstract Iterable<String> TabComplete(CommandSender Sender, String[] Args);

    protected class Build extends net.md_5.bungee.api.plugin.Command implements net.md_5.bungee.api.plugin.TabExecutor {
        public Build(String Name) {
            super(Name);
        }

        @Override
        public void execute(CommandSender s, String[] a) {
            if (p.Permission() != null && !s.hasPermission(p.Permission())) {
                s.sendMessage(Settings.Settings(id.universenetwork.utilities.Universal.Enums.Settings.NOPERMISSION));
                return;
            }
            if (p.PlayerOnly() && !(s instanceof net.md_5.bungee.api.connection.ProxiedPlayer)) {
                s.sendMessage(Settings.Settings(id.universenetwork.utilities.Universal.Enums.Settings.DENYCONSOLE));
                return;
            }
            Execute(s, a);
        }

        @Override
        public Iterable<String> onTabComplete(CommandSender s, String[] a) {
            if (!p.Permission().equals("") && !s.hasPermission(p.Permission()))
                return java.util.Collections.emptyList();
            return TabComplete(s, a);
        }

        @Override
        public String[] getAliases() {
            return p.Aliases();
        }
    }
}