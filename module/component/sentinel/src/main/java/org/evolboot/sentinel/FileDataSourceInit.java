package org.evolboot.sentinel;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件方案，目前不用
 *
 * @author evol
 */
@Slf4j
@Deprecated
public class FileDataSourceInit implements InitFunc {


    @Override
    public void init() throws Exception {
        log.info("初始化Sentinel");
        String ruleDir = "/opt/" + "/sentinel/rules";
        mkdirIfNotExits(ruleDir);
        String flowRulePath = ruleDir + "/sentinel.json";
        ReadableDataSource<String, List<FlowRule>> ds = new FileRefreshableDataSource<>(
                flowRulePath, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        })
        );
        // 将可读数据源注册至 FlowRuleManager.
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(flowRulePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }

    private void mkdirIfNotExits(String filePath) throws IOException {
        log.info("Sentinel 创建路径:{}", filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }

}