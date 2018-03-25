package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.utils.PointsUtils;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClosestEnemiesProtocolManager extends ProtocolManager {

    public ClosestEnemiesProtocolManager(ProtocolManager nextProtocol) {
        super(nextProtocol);
    }

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().sorted(
                Comparator.comparing(PointsUtils::distance)).filter(point -> point.getEnemies().getNumber() > 0).collect(Collectors.toList());

    }

    @Override
    boolean supports(Protocol protocol) {
        return Protocol.CLOSEST.equals(protocol);
    }
}
