package computer.heather.advancedbackups.network;

import computer.heather.advancedbackups.AdvancedBackups;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public record PacketToastSubscribe(boolean enable) implements CustomPacketPayload {

    public static final Type<PacketToastSubscribe> ID = new CustomPacketPayload.Type<PacketToastSubscribe>(Identifier.parse("advancedbackups:toast_subscribe"));

    public PacketToastSubscribe(boolean enable) {
        this.enable = enable;
    }

    public static final StreamCodec<FriendlyByteBuf, PacketToastSubscribe> CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketToastSubscribe::enable, PacketToastSubscribe::new);


    public static void handle(PacketToastSubscribe message, ServerPlayNetworking.Context context) {

        ServerPlayer player = context.player();

        if (message.enable() && !AdvancedBackups.players.contains(player.getStringUUID())) {
            AdvancedBackups.players.add(player.getStringUUID());
        }
        else if (!message.enable()) {
            AdvancedBackups.players.remove(player.getStringUUID());
        }

    }



    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }



}