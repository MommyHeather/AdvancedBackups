package computer.heather.advancedbackups.client;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;

import computer.heather.advancedbackups.core.CoreCommandSystem;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class AdvancedBackupsClientCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommands.literal("backup").requires((runner) -> {
            return true;
        }).then(ClientCommands.literal("start").executes((runner) -> {
            Minecraft.getInstance().player.connection.sendCommand("backup start");
            return 1;
         }))

         .then(ClientCommands.literal("reload-config").executes((runner) -> {
            Minecraft.getInstance().player.connection.sendCommand("backup reload-config");
            return 1;
         }))

         .then(ClientCommands.literal("reset-chain").executes((runner) -> {
            Minecraft.getInstance().player.connection.sendCommand("backup reset-chain");
            return 1;
         }))

         .then(ClientCommands.literal("snapshot").executes((runner) -> {
            Minecraft.getInstance().player.connection.sendCommand("backup snapshot");
            return 1;
         })

         .then(ClientCommands.argument("name", StringArgumentType.greedyString()).executes((runner) -> {
            ParseResults<FabricClientCommandSource> parseResults = dispatcher.parse(StringArgumentType.getString(runner, "name"), runner.getSource());
            String snapshotName = parseResults.getReader().getString();
            Minecraft.getInstance().player.connection.sendCommand("backup snapshot " + snapshotName);
            return 1;
         })))

         .then(ClientCommands.literal("cancel").executes((runner) -> {
            Minecraft.getInstance().player.connection.sendCommand("backup cancel");
            return 1;
         }))

         .then(ClientCommands.literal("reload-client-config").executes((runner) -> {
            CoreCommandSystem.reloadClientConfig((response) -> {
                runner.getSource().sendFeedback(Component.literal(response));
            });
            return 1;
         }))
    
        );
    }


}