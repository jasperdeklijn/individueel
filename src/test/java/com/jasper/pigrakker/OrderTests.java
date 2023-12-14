package com.jasper.pigrakker;

import com.jasper.pigrakker.model.Order;
import com.jasper.pigrakker.model.Packet;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.Status;
import com.jasper.pigrakker.repository.OrderRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderTests {


    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager entityManager;
    @Test
    @Transactional
    public void saveOrder() {
        // Given
        Packet packet = new Packet();
        packet.setPacketname("TestPacket");
        packet.setContains("test");
        packet.setPrice(20.0);
        packet.setTotalKG(30.0);
        entityManager.persist(packet);
        entityManager.flush();

        Order order = new Order();
        order.setPacket(packet);
        order.setName("John Doe");
        order.setPhone("123-456-7890");
        order.setStatus(Status.NEEDSPAYREQUEST);
        order.setDelivered(false);

        // When
        entityManager.persist(order);
        entityManager.flush();

        // Then
        assertNotNull(order.getId());
        Order savedOrder = entityManager.find(Order.class, order.getId());
        assertNotNull(savedOrder);
        assertEquals("John Doe", savedOrder.getName());
        assertEquals("123-456-7890", savedOrder.getPhone());
        assertEquals(Status.NEEDSPAYREQUEST, savedOrder.getStatus());
        assertEquals(false, savedOrder.getDelivered());
        assertEquals(packet, savedOrder.getPacket());
    }

}