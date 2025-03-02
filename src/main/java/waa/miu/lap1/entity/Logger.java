package waa.miu.lap1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loggers")
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private LocalDateTime timestamp;
    private String principle;
    private String operation;

    public Logger() {
        this.timestamp = LocalDateTime.now(); // Set default timestamp
    }

    public Logger(String principle, String operation) {
        this();
        this.principle = principle;
        this.operation = operation;
    }

}
