package api.rest.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import api.rest.server.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{


}
