package com.sparta.SpringPrac.service.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommonResponse<T> extends BasicResponse {
    private String state;
    private T Data;

    public CommonResponse(T Data) {
        this.Data = Data;
        if (Data == null){
            this.state = "fail";
        } else {
            this.state = "success";
        }
    }
}
