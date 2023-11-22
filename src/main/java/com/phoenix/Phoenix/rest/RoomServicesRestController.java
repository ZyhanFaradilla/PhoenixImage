package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.dto.roomServices.InsertRoomServicesDTO;
import com.phoenix.Phoenix.dto.roomServices.UpdateRoomServicesDTO;
import com.phoenix.Phoenix.service.RoomServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/roomServices")
public class RoomServicesRestController extends AbstractRestController{
    @Autowired
    private RoomServicesService roomServicesService;

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody InsertRoomServicesDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = roomServicesService.insertRoomService(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpdateRoomServicesDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = roomServicesService.updateRoomService(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String employeeNumber){
        var dto = roomServicesService.getUpdateRoomService(employeeNumber);
        return ResponseEntity.status(200).body(dto);
    }
}
