package org.mpei.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mpei.util.Create;
import org.mpei.util.Update;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"hotelId", "name", "description", "address", "rating", "amenities", "rooms"})
public class HotelDto {
    @JsonProperty("hotelId")
    private Long id;
    @NotEmpty(groups = {Create.class, Update.class})
    private String name;
    @NotEmpty(groups = Create.class)
    private String description;
    @NotEmpty(groups = Create.class)
    private String address;
    @Min(value = 1, groups = Create.class)
    @Max(value = 5, groups = Create.class)
    @NotNull(groups = Create.class)
    private Integer rating;
    @NotNull(groups = Create.class)
    private List<String> amenities;
    @NotEmpty(groups = Create.class)
    private List<@Valid RoomDto> rooms;
    @NotEmpty(groups = Create.class)
    private String image;
    @NotEmpty(groups = Create.class)
    private String location;
}
