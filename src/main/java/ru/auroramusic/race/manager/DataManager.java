package ru.auroramusic.race.manager;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.auroramusic.config.Configs;
import ru.auroramusic.race.Ski123Data;
import java.io.*;
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
        try {
            logger.info("File for read: " + file.getAbsolutePath());
            while (true) {
                xml = inputStreamToString(new FileInputStream(file));
                logger.debug("File content: " + xml);
                Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
                value.getResults().forEach(scoreBoardManager::addResult);
                value.getParticipants().forEach(scoreBoardManager::addParticipant);
                Thread.sleep(500L);
            }
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
