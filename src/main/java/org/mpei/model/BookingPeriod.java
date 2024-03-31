package org.mpei.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mpei.dto.RoomDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingPeriod {
    private List<RoomDto> rooms;
    private LocalDate start;
    private LocalDate end;
}
