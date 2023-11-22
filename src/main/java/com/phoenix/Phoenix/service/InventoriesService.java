package com.phoenix.Phoenix.service;

import com.phoenix.Phoenix.dao.InventoriesRepository;
import com.phoenix.Phoenix.dao.RoomInventoriesRepository;
import com.phoenix.Phoenix.dto.inventories.InsertInventoriesDTO;
import com.phoenix.Phoenix.dto.inventories.InventoriesRowDTO;
import com.phoenix.Phoenix.dto.inventories.UpdateInventoriesDTO;
import com.phoenix.Phoenix.entity.Inventories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InventoriesService {
    @Autowired
    private InventoriesRepository inventoriesRepository;

    @Autowired
    private RoomInventoriesRepository roomInventoriesRepository;

    public Page<InventoriesRowDTO> getRowInventories(Integer page){
        var pageable = PageRequest.of(page-1, 10, Sort.by("name"));
        return inventoriesRepository.getRow(pageable);
    }

    public UpdateInventoriesDTO getUpdateInventory(String name){
        var entity = inventoriesRepository.findById(name).get();
        var dto = new UpdateInventoriesDTO(
                entity.getName(),
                entity.getStock(),
                entity.getDescription()
        );
        return dto;
    }

    public UpdateInventoriesDTO updateInventory(UpdateInventoriesDTO dto){
        var entity = new Inventories(
                dto.getName(),
                dto.getDescription(),
                dto.getStock()
        );
        var response = inventoriesRepository.save(entity);
        var responseDto = new UpdateInventoriesDTO(
                response.getName(),
                response.getStock(),
                response.getDescription()
        );
        return responseDto;
    }

    public Inventories insertInventory(InsertInventoriesDTO dto){
        var entity = new Inventories(
                dto.getName(),
                dto.getDescription(),
                dto.getStock()
        );
        return inventoriesRepository.save(entity);
    }

    public void delete(String name){
        inventoriesRepository.deleteById(name);
    }

    public Long countRoomInventories(){
        return roomInventoriesRepository.countRoomInventories();
    }
}
