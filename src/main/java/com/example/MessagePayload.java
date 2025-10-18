package com.example;

import com.example.proto.MessageProtos;
import com.google.protobuf.Message;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MessagePayload(byte[] protobufData) implements CustomPayload {
    public static final Identifier MESSAGE_PAYLOAD_ID = Identifier.of(TestTask.MOD_ID, "protobuf_message");
    public static final Id<MessagePayload> ID = new Id<>(MESSAGE_PAYLOAD_ID);
    public static final PacketCodec<RegistryByteBuf, MessagePayload> CODEC = new PacketCodec<>() {
        @Override
        public MessagePayload decode(RegistryByteBuf buf) {
            int length = buf.readVarInt();
            byte[] data = new byte[length];
            buf.readBytes(data);
            return new MessagePayload(data);
        }

        @Override
        public void encode(RegistryByteBuf buf, MessagePayload payload) {
            buf.writeVarInt(payload.protobufData.length);
            buf.writeBytes(payload.protobufData);
        }
    };

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static MessagePayload fromProtobuf(Message message) {
        return new MessagePayload(message.toByteArray());
    }

    public Message toProtobuf() throws Exception {
        return MessageProtos.Message.parseFrom(protobufData);
    }
}
