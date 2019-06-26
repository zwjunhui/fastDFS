package com.example.entity;

import lombok.Data;

@Data
public class ResBean {
    private Enum code = Status.StateCode.SUCCESS;

    private String msg;

    private String url;

    private String link;

}
