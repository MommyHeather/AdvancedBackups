package computer.heather.advancedbackups;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;

import computer.heather.advancedbackups.core.CoreCommandSystem;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;

public class AdvancedBackupsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> stack) {
        stack.register(Commands.literal("backup").requires((runner) -> {
            return !AdvancedBackups.server.isDedicatedServer() || runner.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
        }).then(Commands.literal("start").executes((runner) -> {
            CoreCommandSystem.startBackup((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            });
            return 1;
         }))

         .then(Commands.literal("reload-config").executes((runner) -> {
            CoreCommandSystem.reloadConfig((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            });
            return 1;
         }))

         .then(Commands.literal("reset-chain").executes((runner) -> {
            CoreCommandSystem.resetChainLength((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            });
            return 1;
         }))

         .then(Commands.literal("snapshot").executes((runner) -> {
            CoreCommandSystem.snapshot((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            }, "snapshot");
            return 1;
         })
         .then(Commands.argument("name", StringArgumentType.greedyString()).executes((runner) -> {
            ParseResults<CommandSourceStack> parseResults = stack.parse(StringArgumentType.getString(runner, "name"), runner.getSource());
            String snapshotName = parseResults.getReader().getString();
            CoreCommandSystem.snapshot((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            }, snapshotName);
            return 1;
         })))

         .then(Commands.literal("cancel").executes((runner) -> {
            CoreCommandSystem.cancelBackup((response) -> {
                runner.getSource().sendSuccess(() -> Component.literal(response), true);
            });
            return 1;
         }))

         .then(Commands.literal("reload-client-config").executes((runner) -> {
            runner.getSource().sendSuccess(() -> Component.literal("This command can only be ran on the client!"), true);
            return 1;
         }))
    
        );
    }


}
