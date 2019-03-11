package com.alibaba.craftsman;

import com.alibaba.cola.dto.Response;
import com.alibaba.craftsman.api.MetricsServiceI;
import com.alibaba.craftsman.dto.MiscMetricAddCmd;
import com.alibaba.craftsman.dto.clientobject.MiscMetricCO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * MiscMetricAddCmdExeTest
 *
 * @author Frank Zhang
 * @date 2019-03-04 1:47 PM
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class MiscMetricAddCmdExeTest {
    @Autowired
    private MetricsServiceI metricsService;

    @Test
    public void testSuccess(){
        MiscMetricAddCmd miscMetricAddCmd = new MiscMetricAddCmd();
        MiscMetricCO miscMetricCO = new MiscMetricCO();
        miscMetricCO.setOwnerId("MiscMetricAddCmdExeTest_09888");
        miscMetricCO.setName("Tech highlight");
        miscMetricCO.setContent("Highlight content");
        miscMetricCO.setDocUrl("docUrl");
        miscMetricCO.setCodeUrl("codeUrl");
        miscMetricAddCmd.setMiscMetricCO(miscMetricCO);

        Response response = metricsService.addMiscMetric(miscMetricAddCmd);

        Assert.assertTrue(response.isSuccess());
    }
}
