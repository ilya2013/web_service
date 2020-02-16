package ru.auroramusic.race.manager;

import org.junit.Test;
import ru.auroramusic.config.ConfigManager;
import ru.auroramusic.config.Configs;

import static org.junit.Assert.*;

public class ScoreBoardManagerTest {

    @Test
    public void getScores() throws InterruptedException {
        Configs configs = new ConfigManager().load("./data/testConfig.properties");
        ScoreBoardManager bm = new ScoreBoardManager(configs);
        Thread.sleep(2000);
        System.out.println(bm.getScores("Мужчины_CCSprintQuali_09_01_11_40"));
    }
}