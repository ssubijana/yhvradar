package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssistAlliesProtocolManagerTest {

    private AssistAlliesProtocolManager protocolManager = new AssistAlliesProtocolManager();


    @Test
    public void shouldReturnPointsWhereNotAlliesAre() {

        List<PoiPresentation> points = protocolManager.applyProtocol(createWithAllies());

        assertThat(points).hasSize(2);
        assertThat(points.get(0).getAllies()).isGreaterThan(0);
        assertThat(points.get(1).getAllies()).isGreaterThan(0);
    }

    private List<PoiPresentation> createWithAllies() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).build();

        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);

        return points;

    }

    @Test
    public void shouldReturnAllPoints() {
        List<PoiPresentation> points = protocolManager.applyProtocol(createWithoutAllies());

        assertThat(points).isEmpty();
    }

    private List<PoiPresentation> createWithoutAllies() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.SOLDIER).build()).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.SOLDIER).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).build();

        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3);

        return points;

    }
}
