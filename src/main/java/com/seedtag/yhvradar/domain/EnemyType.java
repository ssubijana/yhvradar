package com.seedtag.yhvradar.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EnemyType {
    SOLDIER("soldier"),
    MECH("mech");

    private String name;

    private EnemyType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
