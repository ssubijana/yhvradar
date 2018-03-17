package com.seedtag.yhvradar.web.presentation;

import com.seedtag.yhvradar.domain.Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ScanPresentation {

    private List<Protocol> protocols;
    private List<PoiPresentation> scan;

}
