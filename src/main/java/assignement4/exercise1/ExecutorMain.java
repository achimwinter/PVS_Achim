package assignement4.exercise1;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ExecutorMain {

    public static void main(String... args) {
        if (args.length > 0) {
            startHazelcastMaster();
        } else {
            startHazelcastClient();
        }
    }

    private static void startHazelcastMaster() {
        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        fillMap(hazelcastInstance);
    }

    private static void startHazelcastClient() {

        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IExecutorService executorService = hazelcastInstance.getExecutorService("executorService");
        Future<Integer> future = executorService.submit(new Sum());
        int sum = 0;
        try {
            sum = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }


    private static void fillMap(HazelcastInstance hazelcastInstance) {
        Map<String, Integer> map = hazelcastInstance.getMap("numbermap");
        for (Integer i = 1; i < 10; i++) {
            map.put(i.toString(), i);
        }
    }

}

