package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvoidMechProtocolManager extends ProtocolManager {

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().filter(point -> !EnemyType.MECH.equals(point.
                getEnemies().getType())).collect(Collectors.toList());
    }
}
