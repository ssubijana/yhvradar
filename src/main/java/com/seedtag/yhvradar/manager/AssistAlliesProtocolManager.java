package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class AssistAlliesProtocolManager extends ProtocolManager {

    public AssistAlliesProtocolManager(ProtocolManager nextProtocol) {
        super(nextProtocol);
    }

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().filter(point -> point.getAllies() != null && point.getAllies() > 0).collect(Collectors.toList());
    }

    @Override
    boolean supports(Protocol protocol) {
        return Protocol.ASSISTALLIES.equals(protocol);
    }
}
