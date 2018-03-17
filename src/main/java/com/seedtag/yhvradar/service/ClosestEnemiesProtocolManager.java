package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.utils.PointsUtils;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClosestEnemiesProtocolManager extends ProtocolManager {

    @Override
    public List<PoiPresentation> applyProtocol(List<PoiPresentation> points) {
        return points.stream().sorted(
                Comparator.comparing(PointsUtils::distance)).filter(point -> point.getEnemies().getNumber() > 0).collect(Collectors.toList());

    }
}
