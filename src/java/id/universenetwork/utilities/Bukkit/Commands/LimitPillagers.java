package id.universenetwork.utilities.Bukkit.Commands;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static id.universenetwork.utilities.Bukkit.Enums.PillagersLimiter.*;
import static id.universenetwork.utilities.Bukkit.Manager.Config.PLBoolean;
import static id.universenetwork.utilities.Bukkit.Manager.Config.PLString;
import static org.bukkit.Bukkit.getWorlds;

public class LimitPillagers extends id.universenetwork.utilities.Bukkit.Manager.Commands {
    public LimitPillagers() {
        super("LimitPillagers", "Main command of Pillagers Limiter features", "unutilities.command.limitpillagers", false);
    }

    @Override
    public void Execute(CommandSender sender, String[] args) {
        if (PLBoolean(ENABLED)) {
            if (args.length != 1 || !args[0].equalsIgnoreCase("count") && !args[0].equalsIgnoreCase("remove"))
                sender.sendMessage(PLString(MESSAGES_USAGE));
            else {
                int pillagerRemoved;
                Iterator i;
                World world;
                if (args[0].equalsIgnoreCase("count")) {
                    pillagerRemoved = 0;
                    for (i = getWorlds().iterator(); i.hasNext(); pillagerRemoved += world.getEntitiesByClasses(new Class[]{Pillager.class}).size())
                        world = (World) i.next();
                    sender.sendMessage(PLString(MESSAGES_COUNT).replace("%count%", Integer.toString(pillagerRemoved)));
                } else if (args[0].equalsIgnoreCase("remove")) {
                    pillagerRemoved = 0;
                    i = getWorlds().iterator();
                    while (i.hasNext()) {
                        world = (World) i.next();
                        for (Iterator var7 = world.getEntitiesByClasses(new Class[]{Pillager.class}).iterator(); var7.hasNext(); ++pillagerRemoved)
                            ((Entity) var7.next()).remove();
                        sender.sendMessage(PLString(MESSAGES_REMOVE).replace("%amount%", Integer.toString(pillagerRemoved)));
                    }
                }
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String str, String[] args) {
        List<String> arg = new ArrayList();
        if (args.length == 1 && PLBoolean(ENABLED)) {
            arg.add("count");
            arg.add("remove");
            return arg;
        }
        return java.util.Collections.emptyList();
    }
}
