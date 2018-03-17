package com.seedtag.yhvradar.service;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FurthestEnemiesProtocolManagerTest {

    private FurthestEnemiesProtocolManager protocolManager = new FurthestEnemiesProtocolManager();


    @Test
    public void shouldReturnPointFurhestWithEnemies() {

        List<PoiPresentation> points = protocolManager.applyProtocol(createWithEnemies());

        assertThat(points).hasSize(3);
        assertThat(points.get(0).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(0).getCoordinates().getY()).isEqualTo(20);
        assertThat(points.get(1).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(1).getCoordinates().getY()).isEqualTo(15);
        assertThat(points.get(2).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(2).getCoordinates().getY()).isEqualTo(12);
    }

    private List<PoiPresentation> createWithEnemies() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(20).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(15).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(25).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();

        List<PoiPresentation> points = Arrays.asList(poi1, poi2, poi3, poi4);

        return points;

    }
}
