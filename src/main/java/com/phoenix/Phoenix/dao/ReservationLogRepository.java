package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.reservationLog.ReservationLogDetailDTO;
import com.phoenix.Phoenix.dto.reservationLog.ReservationLogRowDTO;
import com.phoenix.Phoenix.dto.reservationLog.ReservationRoomBookingDTO;
import com.phoenix.Phoenix.dto.reservationLog.TotalCostReservationDTO;
import com.phoenix.Phoenix.entity.Reservations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationLogRepository extends JpaRepository<Reservations, String> {
    @Query("""
            SELECT new com.phoenix.Phoenix.dto.reservationLog.ReservationLogRowDTO(res.code, res.roomNumber,
            res.guestUsername, res.bookDate, res.checkIn, res.checkOut, res.paymentDate)
            FROM Reservations AS res
            WHERE res.roomNumber LIKE %:roomNumber%
            AND res.guestUsername LIKE %:guestUsername%
            AND (:bookDate IS NULL OR res.bookDate LIKE :bookDate) 
            """)
    public Page<ReservationLogRowDTO> getRow(@Param("roomNumber") String roomNumber,
                                             @Param("guestUsername") String guestUsername,
                                             @Param("bookDate") LocalDateTime bookDate, Pageable pageable);

    @Query("""
		SELECT COUNT(res.code)
		FROM Reservations AS res
		WHERE res.code LIKE :segment% 
		""")
    public Long countCodeByPeriod(@Param("segment") String segment);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.reservationLog.ReservationRoomBookingDTO(
            reser.roomNumber, gu.username, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            ro.guestLimit, ro.cost, reser.checkIn, reser.checkOut,
            reser.cost, reser.paymentDate, 
            CASE 
            WHEN reser.reservationMethod = 'OW   ' THEN 'Official Web'
            WHEN reser.reservationMethod = 'OS   ' THEN 'On Site'
            WHEN reser.reservationMethod = 'AW   ' THEN 'Travel Agent Web atau App'
            ELSE 'Undetected'
            END,
            CASE 
            WHEN reser.paymentMethod = 'CC   ' THEN 'Credit Card'
            WHEN reser.paymentMethod = 'DD   ' THEN 'Direct Debit'
            WHEN reser.paymentMethod = 'VO   ' THEN 'Voucher'
            WHEN reser.paymentMethod = 'EM   ' THEN 'Electronic Money'
            WHEN reser.paymentMethod = 'CA   ' THEN 'Cash'
            ELSE 'Undetected'
            END, reser.remark)
            FROM Reservations AS reser
            LEFT JOIN reser.rooms AS ro
            LEFT JOIN reser.guests AS gu
            WHERE gu.username = :username
            AND reser.checkOut > GETDATE()
            """)
    public ReservationRoomBookingDTO getReservation(@Param("username") String username);

    @Query("""
            SELECT COUNT (res.roomNumber)
            FROM Reservations AS res
            WHERE GETDATE() < res.checkOut AND res.checkIn IS NOT NULL
            AND res.roomNumber = :number
            """)
    public Long getCountReservation(@Param("number") String number);

    @Query("""
            SELECT COUNT (res.roomNumber)
            FROM Reservations AS res
            WHERE GETDATE() < res.checkOut AND res.checkIn IS NOT NULL
            AND res.guestUsername = :username
            """)
    public Long getValidReservation(@Param("username") String username);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.reservationLog.TotalCostReservationDTO(SUM(res.cost))
            FROM Reservations AS res
            WHERE MONTH(res.bookDate) = :month 
            AND YEAR(res.bookDate) = :year
            """)
    public TotalCostReservationDTO getTotalIncome(@Param("month") Integer month,
                                                  @Param("year") Integer year);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.reservationLog.ReservationLogDetailDTO(
            res.code, 
            CASE 
            WHEN res.reservationMethod = 'OW   ' THEN 'Official Web'
            WHEN res.reservationMethod = 'OS   ' THEN 'On Site'
            WHEN res.reservationMethod = 'AW   ' THEN 'Travel Agent Web atau App'
            ELSE 'Undetected'
            END, res.roomNumber, ro.floor, 
            CASE 
            WHEN ro.roomType = 'RSB' THEN 'Regular Single Bed'
            WHEN ro.roomType = 'RDB' THEN 'Regular Double Bed'
            WHEN ro.roomType = 'RTB' THEN 'Regular Twin Bed'
            WHEN ro.roomType = 'VSB' THEN 'VIP Single Bed'
            WHEN ro.roomType = 'VDB' THEN 'VIP Double Bed'
            WHEN ro.roomType = 'VTB' THEN 'VIP Twin Bed'
            ELSE 'Undetected'
            END, 
            res.guestUsername, CONCAT(gu.firstName, ' ', gu.middleName, ' ', gu.lastName),
            res.bookDate, res.checkIn, res.checkOut, res.cost, res.paymentDate,
            CASE 
            WHEN res.paymentMethod = 'CC   ' THEN 'Credit Card'
            WHEN res.paymentMethod = 'DD   ' THEN 'Direct Debit'
            WHEN res.paymentMethod = 'VO   ' THEN 'Voucher'
            WHEN res.paymentMethod = 'EM   ' THEN 'Electronic Money'
            WHEN res.paymentMethod = 'CA   ' THEN 'Cash'
            ELSE 'Undetected'
            END, res.remark)
            FROM Reservations AS res
            JOIN res.rooms AS ro
            JOIN res.guests AS gu
            WHERE res.code = :code
            """)
    public ReservationLogDetailDTO getDetailReservation(@Param("code") String code);

    @Query(nativeQuery = true, value = "EXECUTE AnnualIncome @year = :year")
    public List<Object> executeAnnualIncome(@Param("year") Integer year);

    @Query("""
		SELECT DISTINCT YEAR(res.bookDate)
		FROM Reservations AS res 
		ORDER BY YEAR(res.bookDate) DESC""")
    public List<Integer> getReservationYears();
}
