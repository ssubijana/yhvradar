package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class AvoidMechProtocolManager extends ProtocolManager {

    public AvoidMechProtocolManager(ProtocolManager nextProtocol) {
        super(nextProtocol);
    }

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().filter(point -> !EnemyType.MECH.equals(point.
                getEnemies().getType())).collect(Collectors.toList());
    }

    @Override
    boolean supports(Protocol protocol) {
        return Protocol.AVOIDMECH.equals(protocol);
    }
}
