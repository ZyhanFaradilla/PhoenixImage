package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.dto.account.AdministratorsRowDTO;
import com.phoenix.Phoenix.entity.Administrators;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministratorsRepository extends JpaRepository<Administrators, String> {
    @Query("""
            SELECT new com.phoenix.Phoenix.dto.account.AdministratorsRowDTO(admin.username, admin.jobTitle)
            FROM Administrators AS admin
            """)
    public Page<AdministratorsRowDTO> getRow(Pageable pageable);

    @Query("""
            SELECT COUNT(admin.username)
            FROM Administrators AS admin
            WHERE admin.username = :username
            """)
    public Long countExistingUsername(@Param("username") String username);
}
