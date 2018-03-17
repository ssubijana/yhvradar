package com.seedtag.yhvradar.controller;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.service.RadarServiceImpl;
import com.seedtag.yhvradar.web.RadarController;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import com.seedtag.yhvradar.web.presentation.ScanPresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RadarControllerTest {

    @Mock
    private RadarServiceImpl radarService;

    @InjectMocks
    private RadarController radarController;


    @Test
    public void shouldCallServiceAndReturnPoint() {
        List<Protocol> protocols = Arrays.asList(Protocol.ASSISTALLIES, Protocol.CLOSEST);
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(300).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(400).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(200).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PossitionPresentation expectedPoint = PossitionPresentation.builder().x(1).y(4000).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(expectedPoint).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();
        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);

        ScanPresentation scanData = ScanPresentation.builder().protocols(protocols).scan(points).build();

        when(radarService.findPointToAttack(scanData)).thenReturn(expectedPoint);

        ResponseEntity<PossitionPresentation> attackPoint = radarController.radar(scanData);

        assertThat(attackPoint.getBody()).isEqualTo(expectedPoint);
    }

    @Test
    public void shouldReturnNotFound() {
        List<Protocol> protocols = Arrays.asList(Protocol.ASSISTALLIES, Protocol.CLOSEST);
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(300).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(400).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(200).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PossitionPresentation expectedPoint = PossitionPresentation.builder().x(1).y(4000).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(expectedPoint).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();
        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);

        ScanPresentation scanData = ScanPresentation.builder().protocols(protocols).scan(points).build();

        when(radarService.findPointToAttack(scanData)).thenReturn(null);

        ResponseEntity<PossitionPresentation> attackPoint = radarController.radar(scanData);

        assertThat(attackPoint.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
