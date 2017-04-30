package com.zale.tools.clawer.web.controller;

import com.zale.tools.task.TaskExecutor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoyang on 14/01/2017.
 */
@RestController
public class PingController extends BaseController {
    @Autowired
    private TaskExecutor taskExecutor;
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<TaskExecutor> ping(HttpServletRequest request) {
        return getJsonResp(taskExecutor, HttpStatus.OK);
    }

}
