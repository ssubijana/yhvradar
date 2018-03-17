package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AvoidMechProtocolManagerTest {

    private AvoidMechProtocolManager protocolManager = new AvoidMechProtocolManager();


    @Test
    public void shouldFilterPointsWhereMechAre() {
        List<PoiPresentation> points = protocolManager.applyProtocol(createWithMechs());

        assertThat(points).hasSize(1);
    }

    private List<PoiPresentation> createWithMechs() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).build();

        List<PoiPresentation> points = new ArrayList<PoiPresentation>();
        points.add(poi1);
        points.add(poi2);
        points.add(poi3);

        return points;

    }

    @Test
    public void shouldFilterPointsWhereMechAreNot() {
        List<PoiPresentation> points = protocolManager.applyProtocol(createWithoutMechs());

        assertThat(points).hasSize(3);
    }

    private List<PoiPresentation> createWithoutMechs() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.SOLDIER).build()).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.SOLDIER).build()).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).build();

        List<PoiPresentation> points = new ArrayList<PoiPresentation>();
        points.add(poi1);
        points.add(poi2);
        points.add(poi3);

        return points;

    }
}
