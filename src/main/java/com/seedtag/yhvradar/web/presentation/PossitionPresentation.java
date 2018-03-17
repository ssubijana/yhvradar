package com.seedtag.yhvradar.web.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PossitionPresentation {

    private int x;
    private int y;

}
