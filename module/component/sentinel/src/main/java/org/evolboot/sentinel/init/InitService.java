package org.evolboot.sentinel.init;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.sentinel.MysqlSentinelDataSource;
import org.evolboot.sentinel.acl.port.SentinelConfigurationPort;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class InitService implements ApplicationRunner {

    private final SentinelConfigurationPort sentinelConfigurationPort;

    public InitService(SentinelConfigurationPort sentinelConfigurationPort) {
        this.sentinelConfigurationPort = sentinelConfigurationPort;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化Sentinel");
        MysqlSentinelDataSource mysqlSentinelDataSource = new MysqlSentinelDataSource(sentinelConfigurationPort);
        FlowRuleManager.register2Property(mysqlSentinelDataSource.getProperty());
        WritableDataSourceRegistry.registerFlowDataSource(mysqlSentinelDataSource);
    }
}
