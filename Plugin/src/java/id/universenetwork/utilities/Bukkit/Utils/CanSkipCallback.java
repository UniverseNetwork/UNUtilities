package id.universenetwork.utilities.Bukkit.Utils;

@lombok.Data
public class CanSkipCallback {
    final org.bukkit.command.CommandSender sender;
    final boolean canSkip;
    final java.util.List<String> reason;
}