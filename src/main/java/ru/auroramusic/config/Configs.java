package ru.auroramusic.config;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configs {
    private final File confFile;
    private final Map<String, String> prop = new HashMap<>();

    public Configs(File confFile) {
        this.confFile = confFile;
    }

    public void init() throws IOException {
        Properties property = new Properties();
        try (Reader rdr = new FileReader(confFile)) {
            property.load(rdr);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        property.forEach((o, o2) -> prop.put((String) o, (String) o2));
    }

    public String get(String key) {
        return prop.get(key);
    }

}
