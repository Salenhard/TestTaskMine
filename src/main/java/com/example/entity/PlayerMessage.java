package com.example.entity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class PlayerMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "text", nullable = false, length = 256)
    private String text;

    public PlayerMessage() {}

    public PlayerMessage(UUID uuid, String text) {
        this.uuid = uuid;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}