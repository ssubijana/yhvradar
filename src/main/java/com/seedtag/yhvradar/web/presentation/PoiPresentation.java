package com.seedtag.yhvradar.web.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PoiPresentation {

    private PossitionPresentation coordinates;
    private EnemyPresentation enemies;
    private Integer allies;
}
