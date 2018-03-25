package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;

import java.util.List;

public class ProtocolRuleEngine {

    private final ProtocolManager firstProtocolManager;

    public ProtocolRuleEngine(ProtocolManager firstProtocolManager) {
        this.firstProtocolManager = firstProtocolManager;
    }

    public List<PoiPresentation> run(List<Protocol> protocols, List<PoiPresentation> points) {
        List<PoiPresentation> filteredPoints = points;
        //2- Apply protocols
        for (Protocol protocol :
                protocols) {
            filteredPoints = firstProtocolManager.run(protocol, filteredPoints);
        }
        return filteredPoints;
    }
}
