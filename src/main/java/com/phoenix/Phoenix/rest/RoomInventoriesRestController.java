package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.dto.inventories.InsertInventoriesDTO;
import com.phoenix.Phoenix.dto.roomInventories.InsertRoomInventoriesDTO;
import com.phoenix.Phoenix.service.RoomInventoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/roomInventories")
public class RoomInventoriesRestController extends AbstractRestController{
    @Autowired
    private RoomInventoriesService roomInventoriesService;

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody InsertRoomInventoriesDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = roomInventoriesService.insertRoomInventories(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/{number}")
    public ResponseEntity<Object> addToCart(@PathVariable(required = true) String number){
        var dto = new InsertRoomInventoriesDTO();
        dto.setRoomNumber(number);
        return ResponseEntity.status(200).body(dto);
    }
}
