package com.binhow.video.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer responsCode;
    private String massage;
    private Map<String, Object> state = new HashMap<>();

    public static R success() {
        R r = new R();
        r.setResponsCode(0);
        r.setMassage("success");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setResponsCode(-1);
        r.setMassage("error");
        return r;
    }

    public R state(String key, Object value) {
        this.state.put(key, value);
        return this;
    }
}
