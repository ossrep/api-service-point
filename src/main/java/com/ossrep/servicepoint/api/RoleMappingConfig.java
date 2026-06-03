package com.ossrep.servicepoint.api;

import java.util.Map;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface RoleMappingConfig {

    Map<String, String> roleMapping();
}
