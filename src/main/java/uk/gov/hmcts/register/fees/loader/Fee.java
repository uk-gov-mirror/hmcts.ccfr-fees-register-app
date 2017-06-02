package uk.gov.hmcts.register.fees.loader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Fee {
    @NonNull
    private String id;
    @NonNull
    private String description;
}
