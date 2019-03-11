package com.alibaba.craftsman;


import com.alibaba.cola.mock.annotation.ColaMockConfig;
import com.alibaba.cola.mock.runner.ColaTestRunner;
import com.alibaba.craftsman.api.MetricsServiceI;
import com.alibaba.craftsman.api.UserProfileServiceI;
import com.alibaba.craftsman.dto.ATAMetricAddCmd;
import com.alibaba.craftsman.dto.CodeReviewMetricAddCmd;
import com.alibaba.craftsman.dto.UserProfileAddCmd;
import com.alibaba.craftsman.dto.clientobject.UserProfileCO;
import com.alibaba.craftsman.mock.MockTestBase;
import com.alibaba.craftsman.tunnel.database.MetricTunnel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(ColaTestRunner.class)
@ColaMockConfig(mocks={MetricTunnel.class})
public class ScoreRecalculateTest extends MockTestBase {
    private String userId;

    @Autowired
    private UserProfileServiceI userProfileService;
    @Autowired
    private MetricsServiceI metricsService;

    @Before
    public void init(){
        userId = "ScoreRecalculateTest" + System.currentTimeMillis();
    }

    @Test
    public void testDevSuccess(){
        UserProfileAddCmd userProfileAddCmd = UserProfileCmdExeTest.prepareCommand(userId, UserProfileCO.DEV_ROLE);
        userProfileService.addUserProfile(userProfileAddCmd);

        ATAMetricAddCmd ataMetricAddCmd = ATAMetricAddCmdExeTest.prepareCommand(userId);
        metricsService.addATAMetric(ataMetricAddCmd);
        metricsService.addATAMetric(ataMetricAddCmd);
        metricsService.addATAMetric(ataMetricAddCmd);
    }

    @Test
    public void testQASuccess(){
        UserProfileAddCmd userProfileAddCmd = UserProfileCmdExeTest.prepareCommand(userId, UserProfileCO.QA_ROLE);
        userProfileService.addUserProfile(userProfileAddCmd);

        CodeReviewMetricAddCmd codeReviewMetricAddCmd = CodeReviewMetricAddCmdExeTest.prepareCodeReviewMetricAddCmd(userId);
        metricsService.addCodeReviewMetric(codeReviewMetricAddCmd);
        metricsService.addCodeReviewMetric(codeReviewMetricAddCmd);
        metricsService.addCodeReviewMetric(codeReviewMetricAddCmd);
    }
}
