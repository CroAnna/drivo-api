package org.croanna.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_logs")
@Getter
@Setter
public class RequestLog {
    @Id
    @GeneratedValue
    private Long id;

    private String method;

    private String path;

    private LocalDateTime timestamp;
}
