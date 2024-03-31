package org.mpei.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rooms", schema = "public")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Double price;
    private Integer capacity;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(Type type, Double price, Integer capacity, Hotel hotel) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
        this.hotel = hotel;
    }
}
