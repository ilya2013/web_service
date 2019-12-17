package ru.auroramusic.config;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigsTest {
    @Test
    public void whenPortSet8080ThenGet8080() throws IOException {
        String expextedPort = "8080";
        String expextedDHTFilePath = "./data/test.csv";
        Configs conf = new Configs(new File("./data/testConfig.properties"));
        conf.init();
        assertThat(conf.get("server.port"), is(expextedPort));
        assertThat(conf.get("server.dht.filePath"), is(expextedDHTFilePath));
    }
}