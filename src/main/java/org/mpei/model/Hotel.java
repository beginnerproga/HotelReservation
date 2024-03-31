package org.mpei.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "hotels", schema = "public")
public class Hotel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String description;
    private String address;
    private Integer rating;
    @ElementCollection(targetClass = Amenity.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<Amenity> amenities;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;
    private String image;
    private String location;

    public Hotel(String name, String description, String address, Integer rating, List<Amenity> amenities, List<Room> rooms, String image, String location) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.rating = rating;
        this.amenities = amenities;
        this.rooms = rooms;
        this.image = image;
        this.location = location;
    }
}
