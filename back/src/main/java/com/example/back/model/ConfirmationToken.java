package com.example.back.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Component
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "confirmation_tokens")
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id")
    private Long tokenId;

    @Column(name="token")
    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private Usuario usuario;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    public ConfirmationToken(Usuario usuario) {
        this.usuario = usuario;
        this.createdDate = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }
}
