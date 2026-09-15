package computer.heather.advancedbackups.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class NetworkHandler {

    public static void sendToClient(ServerPlayer player, PacketBackupStatus packet) {

        ServerPlayNetworking.send(player, packet);

    }



}
