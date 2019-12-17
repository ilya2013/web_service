package ru.auroramusic.config;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private static Configs configs;
    private static final String DEFAULT_CONFIG_FILE_NAME = "./config.properties";

    public Configs load(String fileName){
        if (configs == null) {
            synchronized (this) {
                if (configs == null) {
                    configs = new Configs(new File(fileName));
                    try {
                        configs.init();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return configs;
    }

    public static String get(String cong) {
        if (ConfigManager.configs == null) {
            try {
                new ConfigManager().load(DEFAULT_CONFIG_FILE_NAME).init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configs.get(cong);
    }

}
