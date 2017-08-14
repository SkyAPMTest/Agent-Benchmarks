package redis.clients.jedis;

import java.util.HashMap;
import java.util.Map;
import org.skywalking.apm.benchmark.example.util.Executor;

public class Jedis {

    private Map<String, String> cached = new HashMap<>();

    public Jedis(final String host) {

    }

    public Jedis(final String host, final int port) {

    }

    public String set(final String key, String value) {
        Executor.Instance().doExecute();
        cached.put(key, value);
        return value;
    }

    public String get(final String key) {
        Executor.Instance().doExecute();
        return cached.get(key);
    }

}
