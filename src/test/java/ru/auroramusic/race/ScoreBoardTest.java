package ru.auroramusic.race;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ScoreBoardTest {

    public static String msToString(long ms) {
        long totalSecs = ms/1000;
        long hours = (totalSecs / 3600);
        long mins = (totalSecs / 60) % 60;
        long secs = totalSecs % 60;
        String minsString = (mins == 0)
                ? "00"
                : ((mins < 10)
                ? "0" + mins
                : "" + mins);
        String secsString = (secs == 0)
                ? "00"
                : ((secs < 10)
                ? "0" + secs
                : "" + secs);
        if (hours > 0)
            return hours + ":" + minsString + ":" + secsString;
        else if (mins > 0)
            return mins + ":" + secsString;
        else return ":" + secsString;
    }
    @Test
    public void timeFormatTest() {
        System.out.println(msToString(175972));
    }

    @Test
    public void showTest() throws IOException {
        File file = new File("./data/20200109_Sprint_F_.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
//        ScoreBoard scoreBoard = new ScoreBoard("Test1","Женщины_CCSprintQuali_09_01_11_00");
//        scoreBoard.showAll();
    }

    @Test
    public void scoreTest() throws IOException {
        File file = new File("./data/20200109_Sprint_F_.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
//        ScoreBoard scoreBoard = new ScoreBoard("Test1","Женщины_CCSprintQuali_09_01_11_00");
//        System.out.println(scoreBoard.getScores());
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
        //ByteArrayOutputStream
    }

    @Test
    public void getScores() {
    }
}