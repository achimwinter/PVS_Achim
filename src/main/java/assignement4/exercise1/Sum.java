package assignement4.exercise1;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IMap;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

public class Sum implements Callable<Integer>, Serializable, HazelcastInstanceAware {

    private transient HazelcastInstance hazelcastInstance;


    @Override
    public Integer call() {
        IMap<String, Integer> map = hazelcastInstance.getMap( "numbermap" );
        int result = 0;
        for (String key : map.localKeySet()){
            result += map.get(key);
        }
        System.out.println("local result:" + result);
        return result;

    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
}
