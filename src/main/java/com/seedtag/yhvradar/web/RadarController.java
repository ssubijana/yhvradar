package com.seedtag.yhvradar.web;

import com.seedtag.yhvradar.service.RadarService;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import com.seedtag.yhvradar.web.presentation.ScanPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RadarController {

    @Autowired
    private RadarService radarService;

    @RequestMapping(value = "/radar", method = RequestMethod.POST)
    public ResponseEntity<PossitionPresentation> radar(@RequestBody ScanPresentation scanPresentation) {

        PossitionPresentation pointToAttack = radarService.findPointToAttack(scanPresentation);

        if (pointToAttack != null) {
            return ResponseEntity.ok(pointToAttack);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
