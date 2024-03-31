package org.mpei.repository.jpa.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.mpei.model.Amenity;
import org.mpei.model.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecifications {

    public static Specification<Hotel> withDynamicQuery(String location, List<Amenity> amenities, Integer rating) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.distinct(true);

            if (location != null && !location.isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("location")), location.toLowerCase()));
            }

            if (amenities != null && !amenities.isEmpty()) {
                Join<Hotel, Amenity> join = root.join("amenities", JoinType.INNER);
                predicates.add(join.in(amenities));
            }

            if (rating != null) {
                predicates.add(cb.equal(root.get("rating"), rating));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
