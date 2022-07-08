package com.es.controller;

import com.es.entity.EbookEntry;
import lombok.Data;

import java.io.Serializable;

@Data
public class R implements Serializable {
    private Object data;

    public   R (Object data){
        this.data=data;
    }
}
