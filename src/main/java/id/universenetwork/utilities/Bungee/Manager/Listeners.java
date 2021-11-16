package id.universenetwork.utilities.Bungee.Manager;

import id.universenetwork.utilities.Bungee.Listeners.MaxPlayerChangerListener;
import id.universenetwork.utilities.Bungee.Listeners.StaffListListener;

import static id.universenetwork.utilities.Bungee.UNUtilities.prefix;

public class Listeners {
    public Listeners() {
        System.out.println(prefix + " §eRegistering Listeners...");
        id.universenetwork.utilities.Bungee.Utils.Event.registerListeners(
                new MaxPlayerChangerListener(),
                new StaffListListener()
        );
        System.out.println(prefix + " §aAll Listeners Successfully Registered");
    }
}