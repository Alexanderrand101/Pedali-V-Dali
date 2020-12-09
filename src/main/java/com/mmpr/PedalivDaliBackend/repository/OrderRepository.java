package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(UUID id);

    List<Order> findAllByUser_Id(Long user_id);
}
