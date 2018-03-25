package com.seedtag.yhvradar.manager;


import com.seedtag.yhvradar.domain.Protocol;
import org.junit.Before;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.when;

public class ProtocolRuleEngineTest {

    @Mock
    private AvoidMechProtocolManager avoidMechProtocolManager;

    @Mock
    private AssistAlliesProtocolManager assistAlliesProtocolManager;

    private List<ProtocolManager> protocolManagers;

    private ProtocolRuleEngine ruleEngine;

}
