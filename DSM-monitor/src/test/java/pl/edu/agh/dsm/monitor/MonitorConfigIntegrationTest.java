package pl.edu.agh.dsm.monitor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MonitorConfig.class)
@ActiveProfiles("mockComponents")
public class MonitorConfigIntegrationTest {

    @Autowired
    Environment environment;

    @Value("${catalogue.address}")
    String catalogueAdress;


    @Test
    public void testInitObj() throws Exception {
        Assert.assertNotNull(environment);
    }

    @Test
    public void testPropertiesFromEnv() throws Exception {
        String property = environment.getProperty("catalogue.address");
        Assert.assertEquals("http://localhost:8080", property);
    }

    @Test
    public void testPropertyFromValue() throws Exception {
        Assert.assertEquals("http://localhost:8080",catalogueAdress);
    }
}
