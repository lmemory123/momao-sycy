
package com.momao.sycy.service.strategy;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginStrategyFactory {

    private final Map<String, LoginStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public LoginStrategyFactory(Map<String, LoginStrategy> strategyMap) {
        this.strategyMap.putAll(strategyMap);
    }

    public LoginStrategy getStrategy(String accountType) {
        String strategyName = accountType.substring(0, 1).toUpperCase() +
                            accountType.substring(1) + "LoginStrategy";
        return strategyMap.get(strategyName);
    }
}
