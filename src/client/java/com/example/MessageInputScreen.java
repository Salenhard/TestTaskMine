package com.example;
import com.google.protobuf.Message;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.text.Text;

public class MessageInputScreen extends CottonClientScreen {
    private WTextField textField;

    public MessageInputScreen() {
        super(new LightweightGuiDescription()); // Используем сетку для расположения элементов

        WGridPanel root = new WGridPanel();
        textField = new WTextField(); // Создаём поле для ввода текста
        root.add(textField, 0, 0, 4, 1); // Добавляем поле в сетку

        WButton sendButton = new WButton(Text.literal("Отправить")); // Создаём кнопку
        root.add(sendButton, 4, 0, 1, 1); // Добавляем кнопку в сетку

        // Обработчик нажатия на кнопку
        sendButton.setOnClick(() -> {
            String messageText = textField.getText();
            if (!messageText.isEmpty()) {
                // Закрываем экран и отправляем сообщение на сервер
                this.client.setScreen(null);
                ClientPlayNetworking.send((CustomPayload) PacketByteBufs.create().writeByteArray(createProtobufMessage(messageText).toByteArray()));
            }
        });
    }

    private Message createProtobufMessage(String text) {
        return Message.newBuilder().setText(text).build();
    }
}