package com.seedtag.yhvradar.web.presentation;

import com.seedtag.yhvradar.domain.EnemyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EnemyPresentation {
    private EnemyType type;
    private int number;
}
