package com.test.dev.base.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientBaseResponse<T> implements Serializable {

    private String status;
    private String message;
    private T data;

}
