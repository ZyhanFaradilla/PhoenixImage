package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.rooms.RoomDetailBookingDTO;
import com.phoenix.Phoenix.dto.rooms.RoomHeaderDTO;
import com.phoenix.Phoenix.dto.rooms.RoomHeaderReservationDTO;
import com.phoenix.Phoenix.dto.rooms.RoomRowDTO;
import com.phoenix.Phoenix.entity.Rooms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Rooms, String> {
    @Query("""
            SELECT new com.phoenix.Phoenix.dto.rooms.RoomRowDTO(ro.number, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            ro.guestLimit, ro.cost, ro.imgPath)
            FROM Rooms AS ro
            WHERE ro.number LIKE %:number%
            AND ro.roomType LIKE %:type%
            """)
    public Page<RoomRowDTO> getRow(@Param("number") String number, @Param("type") String type, Pageable pageable);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.rooms.RoomDetailBookingDTO(
            ro.number, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            ro.guestLimit, ro.cost, ro.description)
            FROM Rooms AS ro
            WHERE ro.number = :number
            """)
    public RoomDetailBookingDTO getDetailRoom(@Param("number") String number);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.rooms.RoomHeaderReservationDTO(ro.number, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            ro.guestLimit, ro.cost)
            FROM Rooms AS ro
            WHERE ro.number = :number
            """)
    public RoomHeaderReservationDTO getHeaderRoomReservation(@Param("number") String number);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.rooms.RoomHeaderDTO(ro.number, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            ro.guestLimit)
            FROM Rooms AS ro
            WHERE ro.number = :number
            """)
    public RoomHeaderDTO getHeaderRoom(@Param("number") String number);
}
