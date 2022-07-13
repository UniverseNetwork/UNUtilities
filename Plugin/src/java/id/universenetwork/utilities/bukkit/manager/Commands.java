package id.universenetwork.utilities.bukkit.manager;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.exceptions.NoPermissionException;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import id.universenetwork.utilities.bukkit.templates.Command;
import id.universenetwork.utilities.bukkit.UNUtilities;
import id.universenetwork.utilities.bukkit.utils.Logger;
import id.universenetwork.utilities.bukkit.utils.Text;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;

import java.util.function.Function;
import java.util.logging.Level;

public final class Commands {
    @Getter
    static AnnotationParser<CommandSender> annotationParser;
    @Getter
    static PaperCommandManager<CommandSender> manager;

    @SneakyThrows
    public static void init() {
        Logger.info("&eInitializing Command Manager...");
        Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction = CommandExecutionCoordinator.simpleCoordinator();
        Function<CommandSender, CommandSender> mapperFunction = Function.identity();
        manager = new PaperCommandManager<>(UNUtilities.plugin, executionCoordinatorFunction, mapperFunction, mapperFunction);
        Function<ParserParameters, CommandMeta> commandMetaFunction = p -> CommandMeta.simple().with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description")).build();
        annotationParser = new AnnotationParser<>(manager, CommandSender.class, commandMetaFunction);
        if (manager.queryCapability(CloudBukkitCapabilities.BRIGADIER)) manager.registerBrigadier();
        if (manager.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION))
            manager.registerAsynchronousCompletions();
        manager.registerExceptionHandler(NoPermissionException.class, (s, e) -> Text.send(s, UNUtilities.cfg.getString("Settings.no-perm")));
        Logger.info("&aCommand Manager has been initialized!");
    }

    public static void register(Command cmd) {
        try {
            annotationParser.parse(cmd);
        } catch (Exception e) {
            Logger.log(Level.SEVERE, "Failed to register command class: &e" + cmd.getClass().getSimpleName(), e);
        }
    }
}