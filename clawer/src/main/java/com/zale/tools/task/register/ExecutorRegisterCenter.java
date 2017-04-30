package com.zale.tools.task.register;

import java.util.*;

import com.zale.tools.task.TaskExecutor;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Zale on 2017/4/16.
 */
public class ExecutorRegisterCenter {
    private static final Map<String,TaskExecutor> executors = new HashMap<>();
    private static final List<TaskExecutor> weightList = new ArrayList<>();
    public static void registerExecutor(TaskExecutor executor){
        if(executor == null){
            return;
        }
        if(StringUtils.isEmpty(executor.getCallbackAddr())){
            throw new RuntimeException("Callback Address is null");
        }
        executors.put(executor.getCallbackAddr(), executor);
    }
    public static Collection<TaskExecutor> getTaskExecutors(){
        return executors.values();
    }

    public static void removeExecutor(String addr){
        executors.remove(addr);
    }
    public static TaskExecutor getExecutor(String callBackURL){
        return executors.get(callBackURL);
    }

    public static TaskExecutor getRandomExecutor(){
        if(weightList.isEmpty()) {
            List<TaskExecutor> list = new ArrayList<>(executors.values());
            list.forEach(te ->{
                for (int i = 0; i <te.getWeight() ; i++) {
                    weightList.add(te);
                }
            });

        }
        return weightList.get(getRandomIndex(0,weightList.size()));

    }
    private static int getRandomIndex(int start,int end){
        int rNo = RandomUtils.nextInt(start,end);
        if(!weightList.get(rNo).getRunning()){
            return rNo;
        }
        return getRandomIndex(start, end);
    }


}
