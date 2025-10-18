package com.example;

import com.google.protobuf.Message;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ClientPacketSender {
    public static void sendMessage(Message msg) {
        MessagePayload messagePayload = MessagePayload.fromProtobuf(msg);
        ClientPlayNetworking.send(messagePayload);
    }
}
