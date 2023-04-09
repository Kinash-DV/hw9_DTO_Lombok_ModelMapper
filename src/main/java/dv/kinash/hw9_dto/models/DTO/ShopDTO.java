package dv.kinash.hw9_dto.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    Integer id;
    String name;
    String city;
    String street;
    Boolean hasWebsite;
}
