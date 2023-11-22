package com.phoenix.Phoenix.dao;

import com.phoenix.Phoenix.entity.Guests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuestRepository extends JpaRepository<Guests, String> {
    @Query("""
            SELECT COUNT(gu.username)
            FROM Guests AS gu
            WHERE gu.username = :username
            """)
    public Long countExistingUsername(@Param("username") String username);
}
