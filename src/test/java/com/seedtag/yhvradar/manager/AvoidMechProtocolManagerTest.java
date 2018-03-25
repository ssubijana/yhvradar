package com.seedtag.yhvradar.manager;

import com.seedtag.yhvradar.domain.EnemyType;
import com.seedtag.yhvradar.domain.Protocol;
import com.seedtag.yhvradar.manager.AvoidMechProtocolManager;
import com.seedtag.yhvradar.web.presentation.EnemyPresentation;
import com.seedtag.yhvradar.web.presentation.PoiPresentation;
import com.seedtag.yhvradar.web.presentation.PossitionPresentation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AvoidMechProtocolManagerTest {

    @Mock
    private AssistAlliesProtocolManager nextProtocolManager;

    private AvoidMechProtocolManager protocolManager;

    @Before
    public void initData() {
        protocolManager = new AvoidMechProtocolManager(nextProtocolManager);
    }


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

    @Test
    public void shouldRunNextProtocol() {
        List<PoiPresentation> points = createWithMechs();

        protocolManager.run(Protocol.ASSISTALLIES, points);

        verify(nextProtocolManager).run(Protocol.ASSISTALLIES, points);
    }

    @Test
    public void shouldApplyProtocolAndFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.AVOIDMECH, createWithMechs());
        assertThat(points).hasSize(1);
        verify(nextProtocolManager, never()).run(any(), any());
    }

    @Test
    public void shouldApplyProtocolAndNotFilterPoints() {
        List<PoiPresentation> points = protocolManager.run(Protocol.AVOIDMECH, createOnlyWithMechs());
        assertThat(points).hasSize(3);
        verify(nextProtocolManager, never()).run(any(), any());
    }

    private List<PoiPresentation> createOnlyWithMechs() {
        PoiPresentation poi1 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(1).y(12).build()).enemies(EnemyPresentation.builder().number(12).type(EnemyType.MECH).build()).build();
        PoiPresentation poi2 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(1).type(EnemyType.MECH).build()).build();
        PoiPresentation poi3 = PoiPresentation.builder().coordinates(PossitionPresentation.builder().x(12).y(5).build()).enemies(EnemyPresentation.builder().number(34).type(EnemyType.MECH).build()).build();

        return Arrays.asList(poi1, poi2, poi3);
    }

}
