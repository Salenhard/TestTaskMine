package com.example;

import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTask implements ModInitializer {
	public static final String MOD_ID = "testtask";
    public static final Identifier MESSAGE_PACKET_ID = new Identifier(MOD_ID, "message_packet");
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override

	public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(MESSAGE_PACKET_ID, (server, player, handler, buffer, sender) -> {
            byte[] packetData = buffer.readByteArray(); // Читаем массив байтов из пакета
            server.execute(() -> {
                // Обработка на главном игровом потоке
                try {
                    Message protobufMessage;

                    String text = protobufMessage.getText();
                    UUID playerUuid = player.getUuid();

                    // Сохранение в базу данных
                    MessageRepository repository = new MessageRepository();
                    repository.saveMessage(playerUuid, text);

                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            });
        });
	}
}