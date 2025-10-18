package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class TestTaskClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.testtask.open_message_screen", // Идентификатор
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "category.testtask.test"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new MessageScreen(Text.literal("Message Screen")));
                }
            }
        });
	}
}