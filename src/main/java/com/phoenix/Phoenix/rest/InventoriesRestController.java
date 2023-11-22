package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.dto.inventories.InsertInventoriesDTO;
import com.phoenix.Phoenix.dto.inventories.UpdateInventoriesDTO;
import com.phoenix.Phoenix.service.InventoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/inventories")
public class InventoriesRestController extends AbstractRestController{
    @Autowired
    private InventoriesService inventoriesService;

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody InsertInventoriesDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = inventoriesService.insertInventory(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpdateInventoriesDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = inventoriesService.updateInventory(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String name){
        var dto = inventoriesService.getUpdateInventory(name);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String name){
        var depedencies = inventoriesService.countRoomInventories();
        if(depedencies == 0){
            inventoriesService.delete(name);
            return ResponseEntity.status(200).body(depedencies);
        }
        return ResponseEntity.status(200).body(depedencies);
    }
}
