package org.mpei.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mpei.model.Type;
import org.mpei.util.Create;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    @NotNull(groups = Create.class)
    private Type type;
    @NotNull(groups = Create.class)
    private Double price;
    @NotNull(groups = Create.class)
    @DecimalMin(value = "1")
    @DecimalMax(value = "4")
    private Integer capacity;

    public RoomDto(Type type, Double price, Integer capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }
}
