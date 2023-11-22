package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.InventoriesRepository;
import com.phoenix.Phoenix.dao.RoomInventoriesRepository;
import com.phoenix.Phoenix.dto.roomInventories.InsertRoomInventoriesDTO;
import com.phoenix.Phoenix.dto.roomInventories.RoomInventoriesRowDTO;
import com.phoenix.Phoenix.dto.rooms.InsertRoomsDTO;
import com.phoenix.Phoenix.dto.rooms.UpdateRoomsDTO;
import com.phoenix.Phoenix.dto.utility.DropdownDTO;
import com.phoenix.Phoenix.entity.RoomInventories;
import com.phoenix.Phoenix.entity.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInventoriesService {
    @Autowired
    private RoomInventoriesRepository roomInventoriesRepository;

    @Autowired
    private InventoriesRepository inventoriesRepository;

    public Page<RoomInventoriesRowDTO> getDetailRoom(String number, Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("id"));
        return roomInventoriesRepository.getRow(number, pageable);
    }

    public RoomInventories insertRoomInventories(InsertRoomInventoriesDTO dto) {
        var inventories = inventoriesRepository.findById(dto.getInventoryName()).get();
        var entity = new RoomInventories();
        entity.setId(dto.getId());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setInventoryName(dto.getInventoryName());
        entity.setQuantity(dto.getQuantity());
        inventories.setStock(inventories.getStock()- dto.getQuantity());
        inventoriesRepository.save(inventories);
        return roomInventoriesRepository.save(entity);

    }

    public List<DropdownDTO> getDropdownInventories(){
        return inventoriesRepository.getInventoriesDropdown();
    }

    public void delete(Long id){
        roomInventoriesRepository.deleteById(id);
    }

}
