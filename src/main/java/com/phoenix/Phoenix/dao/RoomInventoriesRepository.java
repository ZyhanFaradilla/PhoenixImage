package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.roomInventories.RoomInventoriesRowDTO;
import com.phoenix.Phoenix.entity.RoomInventories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomInventoriesRepository extends JpaRepository<RoomInventories, Long> {
    @Query("""
            SELECT COUNT(roin.id)
            FROM RoomInventories AS roin
            """)
    public Long countRoomInventories();

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.roomInventories.RoomInventoriesRowDTO(roin.id, roin.roomNumber,
            roin.inventoryName, in.stock, roin.quantity)
            FROM RoomInventories AS roin
            LEFT JOIN roin.inventories AS in
            WHERE roin.roomNumber = :roomNumber
            """)
    public Page<RoomInventoriesRowDTO> getRow(@Param("roomNumber") String roomNumber, Pageable pageable);
}
