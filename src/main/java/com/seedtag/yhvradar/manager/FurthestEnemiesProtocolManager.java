package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.utils.PointsUtils;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class FurthestEnemiesProtocolManager extends ProtocolManager {

    public FurthestEnemiesProtocolManager(ProtocolManager nextProtocol) {
        super(nextProtocol);
    }

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().sorted(
                (p1,p2) -> Double.compare(PointsUtils.distance(p2),PointsUtils.distance(p1))).filter(point -> point.getEnemies().getNumber() > 0).collect(Collectors.toList());

    }

    @Override
    boolean supports(Protocol protocol) {
        return Protocol.FURTHEST.equals(protocol);
    }
}
