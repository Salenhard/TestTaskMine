package com.example;

import com.example.entity.PlayerMessage;
import com.example.proto.MessageProtos;
import com.example.repository.MessageRepository;
import com.google.protobuf.InvalidProtocolBufferException;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class TestTask implements ModInitializer {
    public static final String MOD_ID = "testtask";
    public static final Logger log = LoggerFactory.getLogger(MOD_ID);
    private final MessageRepository repository = new MessageRepository();

    @Override

    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(MessagePayload.ID, MessagePayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(MessagePayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                try {
                    log.debug("Received message");
                    MessageProtos.Message message = MessageProtos.Message.parseFrom(payload.protobufData());
                    UUID playerUuid = context.player().getUuid();
                    String text = message.getText();
                    PlayerMessage playerMessage = new PlayerMessage(playerUuid, text);
                    repository.saveMessage(playerMessage);
                    log.debug("Message is saved");
                } catch (InvalidProtocolBufferException e) {
                    log.error("Invalid proto buff");
                    throw new RuntimeException(e);
                }
            });
        });
    }
}