package com.zale.tools.task;

import com.zale.tools.task.process.TaskProcess;
import com.zale.tools.task.register.ExecutorRegisterCenter;
import com.zale.tools.task.utils.ExecutorsPool;
import com.zale.tools.task.utils.HttpClientProvider;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.*;

import java.util.concurrent.Future;

/**
 * Created by Zale on 2017/4/16.
 */
public class SimpleTaskManager {
    private  Map<Integer,List<Task>> taskMap;
    private Future execFuture;

    public  void addTask(Task task){
        addTask(task.getOrder(), Arrays.asList(task));
    }
    public  void addTask(Integer order,List<Task> task){
        if(taskMap == null){
            taskMap = new HashMap<>();
        }
        if(task == null){
            throw new RuntimeException("it should not add null to task pool");
        }
        if(task.stream().anyMatch(t ->t.getOrder()!=order)){
            throw new RuntimeException("setting of order is wrong");
        }
        if(!taskMap.containsKey(order)){
            taskMap.put(order, task);
        }else{
            taskMap.get(order).addAll(task);
        }
    }

    public void start(){
        if(taskMap == null){
            return;
        }
        execFuture =ExecutorsPool.FIXED_EXECUTORS.submit(()->{
            List<List<Task>> tempList = new ArrayList<>();
            tempList.addAll(taskMap.values());
            tempList.sort(Comparator.comparing(t -> t.get(0).getOrder()));
            tempList.forEach(tl->{
                if(tl.size()==1){
                    doLocal(tl.get(0));
                }else {
                    tl.forEach(t -> {
                        TaskExecutor  executor = ExecutorRegisterCenter.getRandomExecutor();
                        if(executor != null) {
                            executor.setRunning(true);
                            if (executor.isLocal()) {
                                doLocal(t);
                                executor.setRunning(false);
                            } else {
                                HttpPost post = HttpClientProvider.getInstance().getHttpPost(executor.getCallbackAddr() + "/task");
                                try {
                                    CloseableHttpResponse response = HttpClientProvider.getInstance().getHttpClient().execute(post);
                                    if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                                        if (t.isSync()) {
                                            executor.setRunning(false);
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            doLocal(t);
                        }

                    });
                }
            });
        });
    }

    private void doLocal(Task t) {
        List<TaskProcess> processList = t.getProcesses();
        if(processList!=null){
            if(t.isSync()) {
                processList.forEach(p -> p.process());
            }else{
                ExecutorsPool.FIXED_EXECUTORS.execute(()->{
                    processList.forEach(p -> p.process());
                });
            }
        }
    }

    public void stop(){
        execFuture.cancel(true);
    }
}
