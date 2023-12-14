package com.jasper.pigrakker;

import com.jasper.pigrakker.model.Packet;
import com.jasper.pigrakker.repository.PacketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PacketTests {

    @Mock
    private PacketRepository packetRepository; // Assuming you have a PacketRepository for data access

    @Test
    public void testPacketEntityFields() {
        Packet packet = new Packet();
        packet.setId(1);
        packet.setPacketname("TestPacket");
        packet.setContains("TestContains");
        packet.setPrice(10.0);
        packet.setTotalKG(5.0);
        packet.setSold(2);

        assertEquals(1, packet.getId());
        assertEquals("TestPacket", packet.getPacketname());
        assertEquals("TestContains", packet.getContains());
        assertEquals(10.0, packet.getPrice());
        assertEquals(5, packet.getTotalKG());
        assertEquals(2, packet.getSold());
    }

    @Test
    public void testPacketEntityRepositorySave() {
        Packet packet = new Packet();
        packet.setPacketname("TestPacket");
        packet.setContains("TestContains");
        packet.setPrice(10.0);
        packet.setTotalKG(5.0);
        packet.setSold(2);

        when(packetRepository.save(packet)).thenReturn(packet);

        Packet savedPacket = packetRepository.save(packet);

        assertNotNull(savedPacket);
        assertEquals("TestPacket", savedPacket.getPacketname());
        assertEquals("TestContains", savedPacket.getContains());
        assertEquals(10.0, savedPacket.getPrice());
        assertEquals(5.0, savedPacket.getTotalKG());
        assertEquals(2, savedPacket.getSold());

        verify(packetRepository, times(1)).save(packet);
    }
}