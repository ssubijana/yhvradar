package com.seedtag.yhvradar.manager;


import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;

import java.util.List;

public abstract class ProtocolManager {

    private ProtocolManager nextProtocol;

    protected ProtocolManager(ProtocolManager nextProtocol) {
        this.nextProtocol = nextProtocol;
    }

    abstract List<PoiPresentation> applyProtocol(List<PoiPresentation> points);


    abstract boolean supports(Protocol protocol);

    public List<PoiPresentation> run(Protocol protocol, List<PoiPresentation> points) {
        List<PoiPresentation> filteredPoints;
        if (supports(protocol)) {
            filteredPoints = applyProtocol(points);
            if (filteredPoints != null && !filteredPoints.isEmpty()) {
                return filteredPoints;
            }
            return points;
        }
        return nextProtocol.run(protocol, points);
    }

}
