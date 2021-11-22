package com.demo.airlinesmanager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class Logger {

    private static final String PRETTY_SEPARATOR = "**********************************************";
    private static final String ERROR_SEPARATOR = "/*~*~*~*~*~*~*~/*~*~*~*~*~*~*~/*~*~*~*~*~*~*~/";
    @Autowired
    private ObjectMapper objectMapper;

    public void receivedRequestWithBody(String lable, String requestType, Object data) {
        separator();
        log.info("{}: {}", lable, requestType);
        if (data != null) infoLogJson("Body", data);
    }

    public void pathVariableData(String lable, String requestType) {
        log.info("{}: {}", lable, requestType);
    }

    public void receivedRequest(String lable, String requestType) {
        separator();
        log.info("{}: {}", lable, requestType);
    }

    public void logResponse(String lable, Object data) {
        infoLogJson(lable, data);
        separator();
    }

    public void infoLog(String lable, String data) {
        log.info("{}: {}", lable, data);
    }

    public void infoLogJson(String lable, Object data) {
        try {
            log.info("{}:\n{}", lable, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
        } catch (Exception ex) {
            errorLog("Print json log exception", ex.getMessage());
        }
    }

    public void warnLog(String lable, String data) {
        log.warn("{}: {}", lable, data);
    }

    public void warnLogJson(String lable, Object data) {
        try {
            log.warn("{}:\n{}", lable, objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
        } catch (Exception ex) {
            errorLog("Print json log exception", ex.getMessage());
        }
    }

    public void errorLog(String lable, String message) {
        System.out.println(ERROR_SEPARATOR);
        log.error("{}: {}", lable, message);
        System.out.println(ERROR_SEPARATOR);
    }

    public void separator() {
        System.out.println(PRETTY_SEPARATOR);
    }
}
