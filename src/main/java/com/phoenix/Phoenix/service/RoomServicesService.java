package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.RoomServicesRepository;
import com.phoenix.Phoenix.dto.roomServices.*;
import com.phoenix.Phoenix.entity.RoomServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomServicesService {
    @Autowired
    private RoomServicesRepository rooomServicesRepository;

    public Page<RoomServicesRowDTO> getRowRoomServices(String employeeNumber,
                                                       String fullName, Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("employeeNumber"));
        return rooomServicesRepository.getRow(employeeNumber, fullName, pageable);
    }

    public UpdateRoomServicesDTO getUpdateRoomService(String employeeNumber){
        var entity = rooomServicesRepository.findById(employeeNumber).get();
        var dto = new UpdateRoomServicesDTO(
                entity.getEmployeeNumber(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getOutsourcingCompany()
        );
        return dto;
    }

    public UpdateRoomServicesDTO updateRoomService(UpdateRoomServicesDTO dto){
        var entity = new RoomServices();
        entity.setEmployeeNumber(dto.getEmployeeNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setOutsourcingCompany(dto.getOutsourcingCompany());
        var response = rooomServicesRepository.save(entity);
        var responseDto = new UpdateRoomServicesDTO(
                response.getEmployeeNumber(),
                response.getFirstName(),
                response.getMiddleName(),
                response.getLastName(),
                response.getOutsourcingCompany()
        );
        return responseDto;
    }

    public RoomServices insertRoomService(InsertRoomServicesDTO dto){
        var entity = new RoomServices();
        entity.setEmployeeNumber(dto.getEmployeeNumber());
        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setOutsourcingCompany(dto.getOutsourcingCompany());
        return rooomServicesRepository.save(entity);
    }

    public RoomServicesHeaderDTO getHeaderRoomServices(String employeeNumber){
        var roomService = rooomServicesRepository.findById(employeeNumber).get();
        var header = new RoomServicesHeaderDTO(
                roomService.getEmployeeNumber(),
                String.format("%s %s %s", roomService.getFirstName(), roomService.getMiddleName(), roomService.getLastName()),
                roomService.getOutsourcingCompany()
        );
        return header;
    }

    public RosterRoomServicesDTO getRoster(String employeeNumber){
        return rooomServicesRepository.getRoster(employeeNumber);
    }

    public RoomServices insertRoster(String employeeNumber, InsertRosterRoomServicesDTO dto){
        var entity = rooomServicesRepository.findById(employeeNumber).get();
        entity.setMondayRosterStart(dto.getMondayRosterStart());
        entity.setMondayRosterFinish(dto.getMondayRosterFinish());
        entity.setTuesdayRosterStart(dto.getTuesdayRosterStart());
        entity.setTuesdayRosterFinish(dto.getTuesdayRosterFinish());
        entity.setWednesdayRosterStart(dto.getWednesdayRosterStart());
        entity.setWednesdayRosterFinish(dto.getWednesdayRosterFinish());
        entity.setThursdayRosterStart(dto.getThursdayRosterStart());
        entity.setThursdayRosterFinish(dto.getThursdayRosterFinish());
        entity.setFridayRosterStart(dto.getFridayRosterStart());
        entity.setFridayRosterFinish(dto.getFridayRosterFinish());
        entity.setSaturdayRosterStart(dto.getSaturdayRosterStart());
        entity.setSaturdayRosterFinish(dto.getSaturdayRosterFinish());
        entity.setSundayRosterStart(dto.getSundayRosterStart());
        entity.setSundayRosterFinish(dto.getSundayRosterFinish());
        return rooomServicesRepository.save(entity);
    }

    public InsertRosterRoomServicesDTO getUpdateRoster(String employeeNumber){
        var entity = rooomServicesRepository.findById(employeeNumber).get();
        var dto = new InsertRosterRoomServicesDTO(
                employeeNumber,
                entity.getMondayRosterStart(),
                entity.getMondayRosterFinish(),
                entity.getTuesdayRosterStart(),
                entity.getTuesdayRosterFinish(),
                entity.getWednesdayRosterStart(),
                entity.getWednesdayRosterFinish(),
                entity.getThursdayRosterStart(),
                entity.getThursdayRosterFinish(),
                entity.getFridayRosterStart(),
                entity.getFridayRosterFinish(),
                entity.getSaturdayRosterStart(),
                entity.getSaturdayRosterFinish(),
                entity.getSundayRosterStart(),
                entity.getSundayRosterFinish()
        );
        return dto;
    }
}
