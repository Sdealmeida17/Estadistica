package org.e2e.labe2e02.passenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerLocationDto {
    private Long id;
    private Double latitude;
    private Double longitude;
}
