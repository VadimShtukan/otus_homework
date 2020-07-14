package stukan.vadim.otus.architect.lesson22.controller;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShardingTest {


    @Test
    void getServerIndex() {
//        UUID uuid = UUID.randomUUID();
        UUID uuid = UUID.fromString("5f326729-6597-418e-bcf6-163eb537e113");
        System.out.println("UUID:" + uuid);

        Sharding sharding = new Sharding();

        String server = sharding.getServerIndex(uuid);

        assertEquals(server, sharding.getServerIndex(uuid));
        assertEquals(server, sharding.getServerIndex(uuid));
        assertEquals(server, sharding.getServerIndex(uuid));
        assertEquals(server, sharding.getServerIndex(uuid));

        System.out.println("Server: " + server);
    }
}