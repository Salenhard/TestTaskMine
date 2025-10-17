package com.example;

import com.google.protobuf.Message;
import com.nimbusds.jose.Payload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ClientPacketSender {
    public static final Identifier MESSAGE_ID = Identifier.of(TestTask.MOD_ID, "message-protobuf");

    public ClientPacketSender() {
        PayloadTypeRegistry.playC2S().register(MessagePayload.ID, MessagePayload.CODEC);
    }

    public static void sendMessage(Message msg) {
        MessagePayload messagePayload = MessagePayload.fromProtobuf(msg);
        ClientPlayNetworking.send(messagePayload);
    }
}
