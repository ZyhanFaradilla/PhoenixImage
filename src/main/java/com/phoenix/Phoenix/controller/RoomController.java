package com.phoenix.Phoenix.controller;

import com.phoenix.Phoenix.dto.rooms.InsertRoomsDTO;
import com.phoenix.Phoenix.dto.rooms.RoomReservationDTO;
import com.phoenix.Phoenix.dto.rooms.UpdateRoomsDTO;
import com.phoenix.Phoenix.dto.utility.DropdownDTO;
import com.phoenix.Phoenix.service.RoomInventoriesService;
import com.phoenix.Phoenix.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomInventoriesService roomInventoriesService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "") String number,
                                   @RequestParam(defaultValue = "") String type,
                                   @RequestParam(defaultValue = "") String status, Model model){
        var rows = roomService.getRowRooms(number, type, status, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("number", number);
        model.addAttribute("type", type);
        model.addAttribute("status", status);
        model.addAttribute("typeDropdown", DropdownDTO.getTypeDropdown());
        model.addAttribute("statusDropdown", DropdownDTO.getStatusDropdown());
        return "rooms/room-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) String number, Model model) {
        model.addAttribute("typeDropdown", DropdownDTO.getTypeDropdown());
        if (number != null) {
            var dto = roomService.getUpdateRoom(number);
            model.addAttribute("dto", dto);
            model.addAttribute("type", "update");
        } else {
            var dto = new InsertRoomsDTO();
            model.addAttribute("dto", dto);
            model.addAttribute("type", "insert");
        }
        return "rooms/rooms-form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("dto") InsertRoomsDTO dto,
                         BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("type", "insert");
            model.addAttribute("typeDropdown", DropdownDTO.getTypeDropdown());
            return "rooms/rooms-form";
        } else {
            roomService.save(dto);
            return "redirect:/rooms/index";
        }
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dto") UpdateRoomsDTO dto,
                         BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("type", "update");
            model.addAttribute("typeDropdown", DropdownDTO.getTypeDropdown());
            return "rooms/rooms-form";
        } else {
            roomService.updateRoom(dto);
            return "redirect:/rooms/index";
        }
    }

    @GetMapping("/detail")
    public String getDetailRoom(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(required = true) String number, Model model){
        var rows = roomInventoriesService.getDetailRoom(number, page);
        var header = roomService.getHeaderRoom(number);
        model.addAttribute("inventoriesDropdown", roomInventoriesService.getDropdownInventories());
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("header", header);
        return "room-inventories/room-inventories-detail";
    }

    @GetMapping("/deleteRoomInventories")
    public String deleteRoomInventories(@RequestParam(required = true) Long id,
                                        @RequestParam(required = true) String roomNumber, RedirectAttributes redirectAttributes){
        roomInventoriesService.delete(id);
        redirectAttributes.addAttribute("number", roomNumber);
        return "redirect:/rooms/detail";
    }

    @GetMapping("/booking")
    public String booking(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String number,
                        @RequestParam(defaultValue = "") String type,
                        @RequestParam(defaultValue = "") String status, Model model){
        var row = roomService.getRowRooms(number, type, status, page);
        model.addAttribute("grid", row);
        model.addAttribute("totalPages", row.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("number", number);
        model.addAttribute("type", type);
        model.addAttribute("status", status);
        model.addAttribute("typeDropdown", DropdownDTO.getTypeDropdown());
        model.addAttribute("statusDropdown", DropdownDTO.getStatusDropdown());
        return "rooms/room-booking-index";
    }

    @GetMapping("/detailRoom")
    public String detailRoom(@RequestParam(required = true) String number, Model model){
        var row = roomService.getDetailRoom(number);
        model.addAttribute("grid", row);
        return "rooms/room-booking-detail";
    }

    @GetMapping("/reservationRoom")
    public String reservationRoom(@RequestParam(required = true) String number,
                                  @RequestParam(required = true) String username, Model model){

        var countReservation = roomService.getValidReservation(username);
        if(countReservation != 0){
            return "rooms/room-reservation-failed";
        }
        var dto = new RoomReservationDTO();
        var header = roomService.getHeaderRoomReservation(number);
        dto.setNumber(number);
        dto.setUsername(username);
        model.addAttribute("dto", dto);
        model.addAttribute("reservationMethodDropdown", DropdownDTO.getReservationMethodDropdown());
        model.addAttribute("paymentMethodDropdown", DropdownDTO.getPaymentMethodDropdown());
        model.addAttribute("header", header);
        return "rooms/room-reservation";
    }

    @PostMapping("/reservation")
    public String reservation(@ModelAttribute("dto") RoomReservationDTO dto){
        roomService.saveReservations(dto);
        return "rooms/room-reservation-success";
    }
}
