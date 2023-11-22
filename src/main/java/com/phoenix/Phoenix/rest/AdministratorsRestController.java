package com.phoenix.Phoenix.rest;

import com.phoenix.Phoenix.dto.account.InsertAdministratorsDTO;
import com.phoenix.Phoenix.dto.account.UpdateAdministratorsDTO;
import com.phoenix.Phoenix.service.AdministratorsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/administrators")
public class AdministratorsRestController extends AbstractRestController{
    @Autowired
    private AdministratorsService administratorsService;

    @PostMapping
    public ResponseEntity<Object> post(@Valid @RequestBody InsertAdministratorsDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = administratorsService.insertAdministrator(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpdateAdministratorsDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            var response = administratorsService.updateAdministrator(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/{username}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String username){
        var dto = administratorsService.getUpdateAdministrator(username);
        return ResponseEntity.status(200).body(dto);
    }

}
