package com.seedtag.yhvradar.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Protocol {
    CLOSEST("closest-enemies"),
    FURTHEST("furthest-enemies"),
    ASSISTALLIES("assist-allies"),
    AVOIDCROSSFIRE("avoid-crossfire"),
    PRIORMECH("prioritize-mech"),
    AVOIDMECH("avoid-mech");

    private String name;

    private Protocol(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
