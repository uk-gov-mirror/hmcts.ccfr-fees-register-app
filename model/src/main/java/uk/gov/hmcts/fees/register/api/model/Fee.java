package uk.gov.hmcts.fees.register.api.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "type")
public abstract class Fee implements Calculateable {
    @Id
    private Integer id;
    @NonNull
    private String code;
    @NonNull
    private String description;
}
