package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.manager.ClosestEnemiesProtocolManager;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ClosestEnemiesProtocolManagerTest {

    @Mock
    private AssistAlliesProtocolManager nextProtocolManager;

    private ClosestEnemiesProtocolManager protocolManager;

    @Before
    public void initData() {
        protocolManager = new ClosestEnemiesProtocolManager(nextProtocolManager);
    }


    @Test
    public void shouldReturnPointClosestWithEnemies() {

        List<PoiPresentation> points = protocolManager.applyProtocol(createWithEnemies());

        assertThat(points).hasSize(3);
        assertThat(points.get(0).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(0).getCoordinates().getY()).isEqualTo(12);
        assertThat(points.get(1).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(1).getCoordinates().getY()).isEqualTo(17);
        assertThat(points.get(2).getCoordinates().getX()).isEqualTo(1);
        assertThat(points.get(2).getCoordinates().getY()).isEqualTo(20);
    }

    private List<PoiPresentation> createWithEnemies() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(20).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(17).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(25).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();

        return Arrays.asList(poi1, poi2, poi3, poi4);
    }

    @Test
    public void shouldRunNextProtocol() {
        List<PoiPresentation> points = createWithEnemies();

        protocolManager.run(Protocol.ASSISTALLIES, points);

        verify(nextProtocolManager).run(Protocol.ASSISTALLIES, points);
    }

    @Test
    public void shouldApplyProtocolAndFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.CLOSEST, createWithEnemies());
        assertThat(points).hasSize(3);
        verify(nextProtocolManager, never()).run(any(), any());
    }

    @Test
    public void shouldApplyProtocolAndNotFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.CLOSEST, createWithoutEnemies());
        assertThat(points).hasSize(4);
        verify(nextProtocolManager, never()).run(any(), any());
    }

    private List<PoiPresentation> createWithoutEnemies() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).allies(12).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(20).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).allies(0).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(17).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.SOLDIER).build()).allies(3).build();
        PoiPresentation poi4 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(25).build()).enemies(EnemyPresentation.builder().number(0).type(EnemyType.MECH).build()).build();

        return Arrays.asList(poi1, poi2, poi3, poi4);
    }

}
