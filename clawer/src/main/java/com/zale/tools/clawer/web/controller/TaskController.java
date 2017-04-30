package com.zale.tools.clawer.web.controller;

import com.zale.tools.task.Task;
import com.zale.tools.task.TaskExecutor;
import com.zale.tools.task.process.TaskProcess;
import com.zale.tools.task.register.ExecutorRegisterCenter;
import com.zale.tools.task.utils.ExecutorsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Zale on 2017/4/17.
 */
@RestController
public class TaskController extends BaseController{
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(HttpServletRequest request,@RequestBody TaskExecutor taskExecutor) {
        ExecutorRegisterCenter.registerExecutor(taskExecutor);
        return getJsonResp("注册成功", HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<String> doTask(@RequestBody Task task){
        List<TaskProcess> processList = task.getProcesses();
        if(processList!=null) {
            if (task.isSync()) {
                processList.forEach(p -> p.process());
                return getJsonResp("执行成功", HttpStatus.OK);
            } else {
                ExecutorsPool.FIXED_EXECUTORS.execute(() -> {
                    processList.forEach(p -> p.process());
                });
                return getJsonResp("执行命令接收成功", HttpStatus.OK);
            }
        }
        return getJsonResp("执行成功", HttpStatus.OK);
    }
}
