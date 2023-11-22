package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.inventories.InventoriesRowDTO;
import com.phoenix.Phoenix.dto.utility.DropdownDTO;
import com.phoenix.Phoenix.entity.Inventories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoriesRepository extends JpaRepository<Inventories, String> {
    @Query("""
            SELECT new com.phoenix.Phoenix.dto.inventories.InventoriesRowDTO(in.name, in.stock, in.description)
            FROM Inventories AS in
            """)
    public Page<InventoriesRowDTO> getRow(Pageable pageable);

    @Query("""
            SELECT new com.phoenix.Phoenix.dto.utility.DropdownDTO(in.name, in.name)
            FROM Inventories AS in
            """)
    public List<DropdownDTO> getInventoriesDropdown();
}
