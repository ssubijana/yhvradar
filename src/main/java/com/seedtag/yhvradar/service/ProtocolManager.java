package com.seedtag.yhvradar.service;


import com.seedtag.yhvradar.web.presentation.PoiPresentation;

import java.util.List;

public abstract class ProtocolManager {

    abstract List<PoiPresentation> applyProtocol(List<PoiPresentation> points);

}
