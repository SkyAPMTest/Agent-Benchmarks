package redis.clients.jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.skywalking.apm.benchmark.example.util.Executor;

public class Jedis {


    public Jedis(final String host) {

    }

    public Jedis(final String host, final int port) {

    }

    public String set(final String key, String value) {
        Executor.Instance().doExecute();
        return value;
    }

    public String get(final String key) {
        Executor.Instance().doExecute();
        return null;
    }

}
