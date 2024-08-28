package am.fillandgo.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;
}
