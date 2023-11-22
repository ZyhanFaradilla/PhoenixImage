package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.roomServices.RoomServicesRowDTO;
import com.phoenix.Phoenix.dto.roomServices.RosterRoomServicesDTO;
import com.phoenix.Phoenix.entity.RoomServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomServicesRepository extends JpaRepository<RoomServices, String> {
    @Query("""
            SELECT new com.phoenix.Phoenix.dto.roomServices.RoomServicesRowDTO(
            rose.employeeNumber, CONCAT(rose.firstName, ' ', rose.middleName, ' ', rose.lastName), rose.outsourcingCompany)
            FROM RoomServices AS rose
            WHERE rose.employeeNumber LIKE %:employeeNumber%
            AND CONCAT(rose.firstName, ' ', rose.middleName, ' ', rose.lastName) LIKE %:fullName%
            """)
    public Page<RoomServicesRowDTO> getRow(@Param("employeeNumber") String employeeNumber,
                                           @Param("fullName") String fullName, Pageable pageable);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.roomServices.RosterRoomServicesDTO(
            rose.mondayRosterStart, rose.mondayRosterFinish, rose.tuesdayRosterStart, rose.tuesdayRosterFinish,
            rose.wednesdayRosterStart, rose.wednesdayRosterFinish, rose.thursdayRosterStart, rose.thursdayRosterFinish,
            rose.fridayRosterStart, rose.fridayRosterFinish, rose.saturdayRosterStart, rose.saturdayRosterFinish,
            rose.sundayRosterStart, rose.sundayRosterFinish)
            FROM RoomServices AS rose
            WHERE rose.employeeNumber = :employeeNumber
            """)
    public RosterRoomServicesDTO getRoster(@Param("employeeNumber") String employeeNumber);
}
