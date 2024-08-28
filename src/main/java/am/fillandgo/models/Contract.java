package am.fillandgo.models;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Contract extends BaseEntity {

    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String mobileNumber;
    private String file;
}
