package org.mpei.repository.impl;

import lombok.RequiredArgsConstructor;
import org.mpei.exception.error_404.RoomNotFoundException;
import org.mpei.model.Room;
import org.mpei.repository.RoomRepository;
import org.mpei.repository.jpa.RoomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoomRepositoryAdapter implements RoomRepository {
    private final RoomJpaRepository roomJpaRepository;

    @Override
    public Room getRoomById(Long id) {
        return roomJpaRepository.findById(id).orElseThrow(() -> new RoomNotFoundException("Room with id = " + id + " not found"));
    }
}
