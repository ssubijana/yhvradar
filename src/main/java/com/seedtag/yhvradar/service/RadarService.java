package com.seedtag.yhvradar.service;


import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import com.seedtag.yhvradar.web.presentation.ScanPresentation;

public interface RadarService {

    PossitionPresentation findPointToAttack(ScanPresentation scanData);
}
