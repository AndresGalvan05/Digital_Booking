package com.example.back.repository;

import com.example.back.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{
    ConfirmationToken findByToken(String confirmationToken);
}
