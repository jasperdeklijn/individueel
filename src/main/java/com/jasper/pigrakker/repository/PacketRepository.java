package com.jasper.pigrakker.repository;

import com.jasper.pigrakker.model.Packet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacketRepository extends JpaRepository<Packet, Long> {
    Optional<Packet> findByPacketname(String packetname);
    Optional<Packet> findById(long id);


}
