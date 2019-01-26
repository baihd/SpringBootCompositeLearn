package com.composite.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("Service")
public class ServiceCfg {

    @XStreamAsAttribute
    private String name;

    private List<ConnectorCfg> connectorCfgs;
}
