package guru.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
public class CustomerDTO {
    private UUID id;
    private Integer version;
    private String customerName;
    private LocalTime createdDate;
    private LocalTime lastModifiedDate;
}
