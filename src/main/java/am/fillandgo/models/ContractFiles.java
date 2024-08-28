package am.fillandgo.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ContractFiles extends BaseEntity{

    private String id;
    private String name;
    private String userId;

}
