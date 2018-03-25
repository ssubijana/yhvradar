package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.manager.*;
import com.seedtag.yhvradar.utils.PointsUtils;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import com.seedtag.yhvradar.web.presentation.ScanPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RadarServiceImpl implements RadarService {

    @Autowired
    private ProtocolRuleEngine ruleEngine;


    @Override
    public PossitionPresentation findPointToAttack(ScanPresentation scanData) {

        if (scanData != null && !scanData.getProtocols().isEmpty()) {

            //1- Filter points further than 100m
            List<PoiPresentation> closestPoints = scanData.getScan().stream().filter(point -> PointsUtils.distance(point) <= 100).collect(Collectors.toList());

            if (closestPoints.isEmpty()) {
                return null;
            }

            List<PoiPresentation> filteredPoints = ruleEngine.run(scanData.getProtocols(), closestPoints);

            //Return first point
            return filteredPoints.get(0).getCoordinates();
        }

        return null;
    }
}
