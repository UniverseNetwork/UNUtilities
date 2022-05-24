package id.universenetwork.utilities.Bukkit.Manager;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.minecraft.extras.MinecraftExceptionHandler;
import cloud.commandframework.minecraft.extras.MinecraftHelp;
import cloud.commandframework.paper.PaperCommandManager;
import id.universenetwork.utilities.Bukkit.Templates.Command;
import id.universenetwork.utilities.Bukkit.UNUtilities;
import id.universenetwork.utilities.Bukkit.Utils.Logger;
import id.universenetwork.utilities.Bukkit.Utils.Text;
import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;

import java.util.function.Function;
import java.util.logging.Level;

public final class Commands {
    @Getter
    static AnnotationParser<CommandSender> annotationParser;
    @Getter
    static PaperCommandManager<CommandSender> manager;
    @Getter
    static MinecraftHelp minecraftHelp;

    @lombok.SneakyThrows
    public static void init() {
        Logger.info("&eInitializing Command Manager...");
        Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction = CommandExecutionCoordinator.simpleCoordinator();
        Function<CommandSender, CommandSender> mapperFunction = Function.identity();
        manager = new PaperCommandManager<>(UNUtilities.plugin, executionCoordinatorFunction, mapperFunction, mapperFunction);
        Function<ParserParameters, CommandMeta> commandMetaFunction = p -> CommandMeta.simple().with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description")).build();
        annotationParser = new AnnotationParser<>(manager, CommandSender.class, commandMetaFunction);
        BukkitAudiences bukkitAudiences = BukkitAudiences.create(UNUtilities.plugin);
        minecraftHelp = new MinecraftHelp<>("/uu help", bukkitAudiences::sender, manager);
        if (manager.queryCapability(CloudBukkitCapabilities.BRIGADIER)) manager.registerBrigadier();
        if (manager.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION))
            manager.registerAsynchronousCompletions();
        new MinecraftExceptionHandler<CommandSender>().withNoPermissionHandler().withDecorator(component -> Component.text().append(Component.text(Text.translateColor(UNUtilities.cfg.getString("Settings.no-perm")))).build()).apply(manager, bukkitAudiences::sender);
        new MinecraftExceptionHandler<CommandSender>().withInvalidSyntaxHandler().withInvalidSenderHandler().withNoPermissionHandler().withArgumentParsingHandler().withCommandExecutionHandler().withDecorator(component -> Component.text().append(Component.text(UNUtilities.prefix)).append(component).build()).apply(manager, bukkitAudiences::sender);
        minecraftHelp.setHelpColors(MinecraftHelp.HelpColors.of(TextColor.color(5592405), TextColor.color(16777045), TextColor.color(11184810), TextColor.color(5635925), TextColor.color(5592405)));
        Logger.info("&aCommand Manager has been initialized!");
    }

    public static void register(Command cmd) {
        try {
            annotationParser.parse(cmd);
        } catch (Exception e) {
            Logger.log(Level.SEVERE, "Failed to register command class: " + cmd.getClass().getName(), e);
        }
    }
}