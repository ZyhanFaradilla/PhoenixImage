package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.ReservationLogRepository;
import com.phoenix.Phoenix.dao.RoomRepository;
import com.phoenix.Phoenix.dto.reservationLog.ReservationLogDetailDTO;
import com.phoenix.Phoenix.dto.reservationLog.ReservationLogRowDTO;
import com.phoenix.Phoenix.dto.reservationLog.ReservationRoomBookingDTO;
import com.phoenix.Phoenix.dto.reservationLog.TotalCostReservationDTO;
import com.phoenix.Phoenix.dto.rooms.RoomHeaderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationLogService {
    @Autowired
    private ReservationLogRepository reservationLogRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Page<ReservationLogRowDTO> getRowReservationLog(String roomNumber, String guestUsername, LocalDateTime bookDate, Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("code"));
        return reservationLogRepository.getRow(roomNumber, guestUsername, bookDate, pageable);
    }

    public ReservationRoomBookingDTO getReservationBooking(String username){
        return reservationLogRepository.getReservation(username);
    }

    public TotalCostReservationDTO getTotalIncome(Integer month, Integer year){
        return reservationLogRepository.getTotalIncome(month, year);
    }

    public ReservationLogDetailDTO detailReservation(String code){
        return reservationLogRepository.getDetailReservation(code);
    }
}
