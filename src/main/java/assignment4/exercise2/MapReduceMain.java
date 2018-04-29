package assignment4.exercise2;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobCompletableFuture;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MapReduceMain {

    public static void main(String... args){
        System.out.println("starting hazelcast");
        final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IMap<String, String> map = hazelcastInstance.getMap("mapReduce");

        System.out.println("starting to read file");
        try {
            readFile("NASA_access_log_Aug95", map);
            System.out.println("File read");
        } catch (IOException e) {
            e.printStackTrace();
        }


        JobTracker jobTracker = hazelcastInstance.getJobTracker( "default" );
        KeyValueSource<String, String> source = KeyValueSource.fromMap( map );
        Job<String, String> job = jobTracker.newJob( source );

        System.out.println("Starting Map reduce");
        JobCompletableFuture<Map<String, Long>> future = job
                .mapper( new TokenizerMapper() )
                .combiner( new WordCountCombinerFactory() )
                .reducer( new WordCountReducerFactory() )
                .submit();

        System.out.println("job completed");
        Map<String, Long> tst = null;

        if (future.isDone()) {
            try {
                tst = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<String, Long> s : tst.entrySet()){
            System.out.println(s.getKey() + ": " + s.getValue());
        }
    }


    private static void readFile(String fileName, IMap<String, String> map) throws IOException {
        Integer counter = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();

            while (line != null) {
                line = line.split("\"")[1];
                String filter = line.split(" ")[0];
                if (filter.equals("GET") || filter.equals("HEAD")) {
                    line = line.split(" ")[1];
                    map.put(counter.toString(), line);
                    counter++;
                }
                line = br.readLine();

            }
        }

    }
}
