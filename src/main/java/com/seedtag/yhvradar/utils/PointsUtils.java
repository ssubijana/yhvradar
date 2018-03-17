package com.seedtag.yhvradar.utils;

import com.seedtag.yhvradar.web.presentation.PoiPresentation;

public class PointsUtils {

    public static Double distance(PoiPresentation point) {
        return Math.sqrt(Math.pow((double)point.getCoordinates().getX(),2) + Math.pow((double)point.getCoordinates().getY(),2));
    }
}
