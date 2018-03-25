package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.manager.AssistAlliesProtocolManager;
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
public class AssistAlliesProtocolManagerTest {

    @Mock
    private AvoidCrossFireProtocolManager nextProtocolManager;

    private AssistAlliesProtocolManager protocolManager;

    @Before
    public void initData() {
        protocolManager = new AssistAlliesProtocolManager(nextProtocolManager);
    }

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

    @Test
    public void shouldRunNextProtocol() {
        List<PoiPresentation> points = createWithAllies();

        protocolManager.run(Protocol.AVOIDMECH, points);

        verify(nextProtocolManager).run(Protocol.AVOIDMECH, points);
    }

    @Test
    public void shouldApplyProtocolAndFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.ASSISTALLIES, createWithAllies());
        assertThat(points).hasSize(2);
        verify(nextProtocolManager, never()).run(any(), any());
    }

    @Test
    public void shouldApplyProtocolAndNotFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.ASSISTALLIES, createWithoutAllies());
        assertThat(points).hasSize(3);
        verify(nextProtocolManager, never()).run(any(), any());
    }
}
