package com.arj.tiketkufinalproject.Response;

import lombok.Data;

@Data
public class CommonResponse<T>{
    private String status;
    private String msg;
    private T datas;

    public CommonResponse() {
    }

    public CommonResponse(String status, String msg, T datas) {
        this.status = status;
        this.msg = msg;
        this.datas = datas;
    }
}
