package com.gabriel.prodmsv.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Getter
@Slf4j
@Component
public class ApplicationUtil {

    private final String prodServiceUrl;

    public ApplicationUtil(@Value("${service.api.endpoint}") final String prodServiceUrl){
        this.prodServiceUrl = prodServiceUrl;
    }
}
