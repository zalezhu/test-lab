package com.zale.tools.clawer.web.controller;

import com.zale.tools.clawer.entity.RespEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by young on 6/11/15.
 */
@RestController
public class BaseController {

    public static final String USER_SESSION_KEY = "USER:SESSION";

    public <T> ResponseEntity<T> getJsonResp(T body, HttpStatus statusCode) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json;charset=utf-8");
        return new ResponseEntity<>(body, responseHeaders, statusCode);
    }

    public ResponseEntity<RespEntity> getMessageResp(String message) {
        RespEntity respEntity = new RespEntity();
        respEntity.setKey(CommonErrCode.NONE.getCode());
        respEntity.setMsg(message);
        return getJsonResp(respEntity, HttpStatus.OK);
    }


}
