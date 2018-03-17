package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.Protocol;
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
    private ClosestEnemiesProtocolManager closestEnemiesProtocolManager;

    @Autowired
    private FurthestEnemiesProtocolManager furthestEnemiesProtocolManager;

    @Autowired
    private AssistAlliesProtocolManager assitAlliesProtocolManager;

    @Autowired
    private AvoidCrossFireProtocolManager avoidCrossFireProtocolManager;

    @Autowired
    private PriorMechProtocolManager priorMechProtocolManager;

    @Autowired
    private AvoidMechProtocolManager avoidMechProtocolManager;


    @Override
    public PossitionPresentation findPointToAttack(ScanPresentation scanData) {

        if (scanData != null && !scanData.getProtocols().isEmpty()) {

            //1- Filter points further than 100m
            List<PoiPresentation> closestPoints = scanData.getScan().stream().filter(point -> PointsUtils.distance(point) <= 100).collect(Collectors.toList());

            if (closestPoints.isEmpty()) {
                return null;
            }

            //2- Apply protocols
            for (Protocol protocol :
                    scanData.getProtocols()) {

                List<PoiPresentation> filteredPoints;
                switch (protocol) {
                    case CLOSEST:
                        filteredPoints = closestEnemiesProtocolManager.applyProtocol(closestPoints);
                        break;
                    case FURTHEST:
                        filteredPoints = furthestEnemiesProtocolManager.applyProtocol(closestPoints);
                        break;
                    case ASSISTALLIES:
                        filteredPoints = assitAlliesProtocolManager.applyProtocol(closestPoints);
                        break;
                    case AVOIDCROSSFIRE:
                        filteredPoints = avoidCrossFireProtocolManager.applyProtocol(closestPoints);
                        break;
                    case PRIORMECH:
                        filteredPoints = priorMechProtocolManager.applyProtocol(closestPoints);
                        break;
                    case AVOIDMECH:
                        filteredPoints = avoidMechProtocolManager.applyProtocol(closestPoints);
                        break;
                    default:
                        filteredPoints = null;
                        break;
                }

                if (filteredPoints != null && !filteredPoints.isEmpty()) {
                    closestPoints = filteredPoints;
                }
            }
            //Return first point
            return closestPoints.get(0).getCoordinates();
        }

        return null;
    }
}
