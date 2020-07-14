package stukan.vadim.otus.architect.lesson22.MongoShardServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;


@Controller
public class ShardingHashFunction {
    @Value("${app.mongo.server1.connection}")
    private String connectionString;// = "mongodb://user:p123456@localhost:27018/cart?authSource=cart&readPreference=primary&ssl=false";

    @Value("${app.mongo.server1.database}")
    private String dbName;// = "cart";

    private final ShardedServer[] shardedServersList = {
            new ShardedServer1(),
            new ShardedServer2()
    };

    private final String[] servers = {"192.168.1.1:111", "192.168.0.0:111", "192.168.0.1:111",
            "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111", "192.168.0.5:111" };

    private final SortedMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
    private final SortedMap<Integer, ShardedServer> shardedServersMap = new TreeMap<Integer, ShardedServer>();

    public ShardingHashFunction() {
        for (int i=0; i<servers.length; i++) {
            int hash = getHash(servers[i]);
            //System.out.println("[" + servers[i] + "]Join the collection, his Hash The value is" + hash);
            sortedMap.put(hash, servers[i]);
        }

        for (int i=0; i<shardedServersList.length; i++) {
            int hash = getHash(shardedServersList[i].toString());
            //System.out.println("[" + servers[i] + "]Join the collection, his Hash The value is" + hash);
            shardedServersMap.put(hash, shardedServersList[i]);
        }
        //System.out.println();
    }

    private String getServer(String key) {
        //Get the hash value of the key
        int hash = getHash(key);
        //Get all Map s that are larger than the Hash value
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //If there is no one larger than the hash value of the key, start with the first node
            Integer i = sortedMap.firstKey();
            //Return to the corresponding server
            return sortedMap.get(i);
        } else {
            //The first Key is the nearest node clockwise past the node.
            Integer i = subMap.firstKey();
            //Return to the corresponding server
            return subMap.get(i);
        }
    }

    private int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // If the calculated value is negative, take its absolute value.
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public String getServerIndex(UUID id){
        return this.getServer(id.toString());

    }

    public ShardedServer getShardedServer(UUID id){
        return this.getShardedServer(id.toString());
    }

    private ShardedServer getShardedServer(String key) {
        int hash = getHash(key);

        //Get all Map s that are larger than the Hash value
        SortedMap<Integer, ShardedServer> subMap = shardedServersMap.tailMap(hash);
        if (subMap.isEmpty()) {
            //If there is no one larger than the hash value of the key, start with the first node
            Integer i = shardedServersMap.firstKey();
            //Return to the corresponding server
            return shardedServersMap.get(i);
        } else {
            //The first Key is the nearest node clockwise past the node.
            Integer i = subMap.firstKey();
            //Return to the corresponding server
            return subMap.get(i);
        }
    }
}
