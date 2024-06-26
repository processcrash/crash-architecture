package com.crush.starter.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author will
 * @since 2024/6/26
 */
@Data
public class JsonResponse<T> implements Serializable {
    
    private String code;
    
    private T data;
    
}
