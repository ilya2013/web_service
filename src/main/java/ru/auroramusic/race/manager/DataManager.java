package ru.auroramusic.race.manager;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.auroramusic.config.Configs;
import ru.auroramusic.race.Ski123Data;

import java.io.*;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;

public class DataManager implements Runnable {
    private final ScoreBoardManager scoreBoardManager;
    private final Configs configs;
    private final static String DEFAULT_RESULTS_FILE = "./data/20200109_Sprint_F_.xml";
    final static Logger logger = Logger.getLogger(DataManager.class);

    public DataManager(ScoreBoardManager scoreBoardManager, Configs configs) {
        this.scoreBoardManager = scoreBoardManager;
        this.configs = configs;
    }

    @Override
    public void run() {
        File file = configs.get("server.race.ski.xml.filePath") != null
                ? new File(configs.get("server.race.ski.xml.filePath"))
                : new File(DEFAULT_RESULTS_FILE);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        while (true) {
            try {
                logger.info("File for read: " + file.getAbsolutePath());
                xml = inputStreamToString(new FileInputStream(file));
                logger.debug("File content: " + xml);
                Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
                value.getResults().forEach(scoreBoardManager::addResult);
                value.getParticipants().forEach(scoreBoardManager::addParticipant);
                Thread.sleep(500L);
            } catch (IOException | InterruptedException e) {
                logger.error(e);
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    logger.error(ex);
                    ex.printStackTrace();
                }
            }
        }
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        logger.info(" File read starting...");
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
