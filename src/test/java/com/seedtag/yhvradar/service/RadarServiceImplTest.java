package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.manager.*;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import com.seedtag.yhvradar.web.presentation.ScanPresentation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RadarServiceImplTest {

    @Mock
    private ProtocolRuleEngine ruleEngine;

    @InjectMocks
    private RadarService radarService = new RadarServiceImpl();

    @Test
    public void shouldFilterCoordinatesFurtherThan100() {
        List<Protocol> protocols = Arrays.asList(Protocol.ASSISTALLIES, Protocol.CLOSEST);
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(300).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(400).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(200).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(4000).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();
        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);

        ScanPresentation scanData = ScanPresentation.builder().protocols(protocols).scan(points).build();

        PossitionPresentation pointToAttack = radarService.findPointToAttack(scanData);

        assertThat(pointToAttack).isNull();
    }

    @Test
    public void shouldReturnCoordinatesPriorMechClosestEnemy100() {
        List<Protocol> protocols = Arrays.asList(Protocol.PRIORMECH, Protocol.CLOSEST);
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(10).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(25).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(4000).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();
        List<PoiPresentation> pointsToPrior = Arrays.asList(poi1, poi2, poi3);
        List<PoiPresentation> filteredPointsToPrior = Arrays.asList(poi1, poi2);


        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);


        ScanPresentation scanData = ScanPresentation.builder().protocols(protocols).scan(points).build();


        when(ruleEngine.run(protocols, pointsToPrior)).thenReturn(filteredPointsToPrior);


        PossitionPresentation pointToAttack = radarService.findPointToAttack(scanData);

        assertThat(pointToAttack).isNotNull();
        assertThat(pointToAttack.getX()).isEqualTo(1);
        assertThat(pointToAttack.getY()).isEqualTo(10);
    }
}
