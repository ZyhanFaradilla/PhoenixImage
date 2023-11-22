package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.ReservationLogRepository;
import com.phoenix.Phoenix.dao.RoomRepository;
import com.phoenix.Phoenix.dto.rooms.*;
import com.phoenix.Phoenix.entity.Reservations;
import com.phoenix.Phoenix.entity.Rooms;
import com.phoenix.Phoenix.utility.FileHelper;
import com.phoenix.Phoenix.utility.MapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationLogRepository reservationLogRepository;

    public Page<RoomRowDTO> getRowRooms(String number, String type, String status, Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("number"));
        var rows = roomRepository.getRow(number, type, pageable);
        var listAvail = new LinkedList<RoomRowDTO>();
        var listBook = new LinkedList<RoomRowDTO>();
        for(RoomRowDTO row: rows){
            var banyak = reservationLogRepository.getCountReservation(row.getNumber());
            if(banyak > 0){
                row.setIsBooked(true);
                row.setStatus("Booking");
                listBook.add(row);
            } else {
                row.setIsBooked(false);
                row.setStatus("Available");
                listAvail.add(row);
            }
        }
        Page<RoomRowDTO> availRows = new PageImpl<>(listAvail);
        Page<RoomRowDTO> bookRows = new PageImpl<>(listBook);
        if (status.equals("Available")){
            return availRows;
        } else if(status.equals("Booking")){
            return  bookRows;
        } else {
            return rows;
        }
    }

    public UpdateRoomsDTO getUpdateRoom(String number) {
        var entity = roomRepository.findById(number).get();
        var room = new UpdateRoomsDTO(
                entity.getNumber(),
                entity.getFloor(),
                entity.getRoomType(),
                entity.getGuestLimit(),
                entity.getCost(),
                entity.getDescription(),
                entity.getImgPath()
        );
        return room;
    }

    public Rooms insertRooms(Object dto) {
        var entity = new Rooms(
             MapperHelper.getStringField(dto, "number"),
                MapperHelper.getIntegerField(dto, "floor"),
                MapperHelper.getStringField(dto, "roomType"),
                MapperHelper.getIntegerField(dto, "guestLimit"),
                MapperHelper.getStringField(dto, "description"),
                MapperHelper.getDoubleField(dto, "cost"),
                MapperHelper.getStringField(dto, "imagePath")
        );
        return roomRepository.save(entity);
    }

    public void save(Object dto) {
        imageFieldHandler((InsertRoomsDTO) dto);
        insertRooms(dto);
    }

    public void updateRoom(UpdateRoomsDTO dto) {
        imageFieldHandler(dto);
        var entity = new Rooms(
                dto.getNumber(),
                dto.getFloor(),
                dto.getRoomType(),
                dto.getGuestLimit(),
                dto.getDescription(),
                dto.getCost(),
                dto.getImagePath()
        );
        roomRepository.save(entity);
    }

    private void imageFieldHandler(InsertRoomsDTO dto){
        var multipartFile = dto.getImage();
        var isMultipartEmpty = multipartFile.isEmpty();
        var imagePath = ((dto.getImagePath() == null || dto.getImagePath().equals(""))
                && isMultipartEmpty) ? null : dto.getImagePath();
        try{
            if(!isMultipartEmpty){
                imagePath = FileHelper.uploadProductPhoto(imagePath, multipartFile);
            }
            dto.setImagePath(imagePath);
        } catch (Exception exception){
            dto.setImagePath(imagePath);
        }
    }

    private void imageFieldHandler(UpdateRoomsDTO dto){
        var multipartFile = dto.getImage();
        var isMultipartEmpty = multipartFile.isEmpty();
        var imagePath = ((dto.getImagePath() == null || dto.getImagePath().equals(""))
                && isMultipartEmpty) ? null : dto.getImagePath();
        try{
            if(!isMultipartEmpty){
                imagePath = FileHelper.uploadProductPhoto(imagePath, multipartFile);
            }
            dto.setImagePath(imagePath);
        } catch (Exception exception){
            dto.setImagePath(imagePath);
        }
    }

    public RoomHeaderDTO getHeaderRoom(String number){
        return roomRepository.getHeaderRoom(number);
    }

    public RoomDetailBookingDTO getDetailRoom(String number){
        return roomRepository.getDetailRoom(number);
    }

    private String getCode(LocalDateTime orderDate){
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        var segment = formatter.format(orderDate);
        var nextRollingNumber = reservationLogRepository.countCodeByPeriod(segment) + 1;
        return String.format("%s-%04d", segment, nextRollingNumber);
    }

    public RoomHeaderReservationDTO getHeaderRoomReservation(String number){
        return roomRepository.getHeaderRoomReservation(number);
    }

    public Reservations saveReservations(RoomReservationDTO dto){
        var room = roomRepository.findById(dto.getNumber()).get();
        var countDays = ChronoUnit.DAYS.between(dto.getCheckIn(), dto.getCheckOut());
        var total = countDays * room.getCost();
        var reservation = new RoomReservationDTO(
                dto.getNumber(),
                dto.getUsername(),
                dto.getCheckIn(),
                dto.getCheckOut(),
                total,
                dto.getReservationMethod(),
                dto.getPaymentMethod(),
                dto.getRemark()
        );
        var bookDate = LocalDateTime.now();
        var entity = new Reservations();
        entity.setCode(getCode(bookDate));
        entity.setReservationMethod(reservation.getReservationMethod());
        entity.setRoomNumber(reservation.getNumber());
        entity.setGuestUsername(reservation.getUsername());
        entity.setBookDate(bookDate);
        entity.setCheckIn(reservation.getCheckIn());
        entity.setCheckOut(reservation.getCheckOut());
        entity.setCost(total);
        entity.setPaymentDate(LocalDateTime.now());
        entity.setPaymentMethod(reservation.getPaymentMethod());
        entity.setRemark(reservation.getRemark());
        return reservationLogRepository.save(entity);
    }

    public Long getValidReservation(String username){
        return reservationLogRepository.getValidReservation(username);
    }
}
