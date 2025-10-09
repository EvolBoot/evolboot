package org.evolboot.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.property.DynamicSentinelProperty;
import com.alibaba.csp.sentinel.property.SentinelProperty;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.sentinel.acl.port.SentinelConfigurationPort;

import java.util.List;

/**
 * 数据源，将规则存储到数据库（配置中心）
 *
 * @author evol
 */
@Slf4j
public class MysqlSentinelDataSource implements ReadableDataSource<String, List<FlowRule>>, WritableDataSource<List<FlowRule>> {

    private final SentinelConfigurationPort sentinelConfigurationPort;
    protected final SentinelProperty<List<FlowRule>> property;

    public MysqlSentinelDataSource(SentinelConfigurationPort sentinelConfigurationPort) {
        this.sentinelConfigurationPort = sentinelConfigurationPort;
        this.property = new DynamicSentinelProperty<>();
        firstLoad();
    }

    private void firstLoad() {
        try {
            List<FlowRule> flowRules = _readSource();
            getProperty().updateValue(flowRules);
        } catch (Throwable e) {
            RecordLog.info("loadConfig exception", e);
        }
    }

    @Override
    public List<FlowRule> loadConfig() {
        log.info("加载配置:loadConfig");
        return _readSource();
    }


    @Override
    public String readSource() {
        log.info("读取数据");
//        String source = "[{\"resource\":\"/api/v1/content/notice/lasest\",\"limitApp\":\"default\",\"grade\":1,\"count\":3.0,\"strategy\":0,\"refResource\":null,\"controlBehavior\":0,\"warmUpPeriodSec\":10,\"maxQueueingTimeMs\":500,\"clusterMode\":false,\"clusterConfig\":{\"flowId\":null,\"thresholdType\":0,\"fallbackToLocalWhenFail\":true,\"strategy\":0,\"sampleCount\":10,\"windowIntervalMs\":1000}}]";
        List<FlowRule> sentinelRole = _readSource();
        return JsonUtil.stringify(sentinelRole);
    }

    @Override
    public SentinelProperty<List<FlowRule>> getProperty() {
        return property;
    }

    @Override
    public void write(List<FlowRule> value) throws Exception {
        log.info("写入数据:{}", JsonUtil.stringify(value));
        save(value);
    }

    @Override
    public void close() throws Exception {
        log.info("关闭Sentinel");
    }


    private List<FlowRule> _readSource() {
        SentinelRole sentinelRole = sentinelConfigurationPort.getSentinelRole();
        return sentinelRole.getList();
    }

    private void save(List<FlowRule> value) {
        SentinelRole sentinelRole = sentinelConfigurationPort.getSentinelRole();
        sentinelRole.setList(value);
        sentinelConfigurationPort.save(sentinelRole);
    }

}
