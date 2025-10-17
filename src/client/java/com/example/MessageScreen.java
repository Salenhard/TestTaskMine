package com.example;

import com.example.proto.MessageProtos;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class MessageScreen extends Screen {

    private TextFieldWidget messageField;
    private ButtonWidget sendButton;
    private final MinecraftClient client;

    protected MessageScreen(Text title) {
        super(title);
        this.client = MinecraftClient.getInstance();
    }

    @Override
    protected void init() {
        messageField = new TextFieldWidget(
                textRenderer,
                this.width / 2 - 100,
                this.height / 2 - 10,
                200,
                20,
                Text.literal("Введите сообщение")
        );
        messageField.setMaxLength(100);
        this.addDrawableChild(messageField);

        sendButton = ButtonWidget.builder(Text.literal("Отправить"), button -> {
                    String message = messageField.getText();
                    sendMessage(message);
                })
                .position(this.width / 2 - 50, this.height / 2 + 20)
                .size(100, 20)
                .build();
        this.addDrawableChild(sendButton);

        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        messageField.render(context, mouseX, mouseY, delta);
    }

    public void sendMessage(String text) {
        messageField.setText("");
        MessageProtos.Message message = MessageProtos.Message.newBuilder().setText(text).build();
        ClientPacketSender.sendMessage(message);
    }
}