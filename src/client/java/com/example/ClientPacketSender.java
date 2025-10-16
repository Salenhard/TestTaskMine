package com.example;

import com.google.protobuf.Message;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ClientPacketSender {
    public static final Identifier MESSAGE_ID = Identifier.of("mymod", "message");

    public static void sendMessage(Message msg) {
        PacketByteBuf buf = new PacketByteBuf(PacketByteBufs.create());
        byte[] data = msg.toByteArray();
        buf.writeByteArray(data);
        ClientSidePacketRegistry.INSTANCE.sendToServer(MESSAGE_ID, buf);
    }
}
