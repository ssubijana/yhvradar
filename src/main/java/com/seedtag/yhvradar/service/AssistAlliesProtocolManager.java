package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssistAlliesProtocolManager extends ProtocolManager {

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().filter(point -> point.getAllies() != null && point.getAllies() > 0).collect(Collectors.toList());
    }
}
