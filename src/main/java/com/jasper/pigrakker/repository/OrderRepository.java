package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteAllByPacket(Packet packet);
    List<Order> findAllByPacket(Packet packet);

}
