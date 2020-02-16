package ru.auroramusic.race;

import com.ctc.wstx.exc.WstxOutputException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.io.*;

import static org.junit.Assert.*;

public class Ski123DataTest {

    @Test
    public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
        File file = new File("./data/20200109_Sprint_F_.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
        value.getResults().forEach(System.out::println);
    }

    @Test
    public void whenJavaGotFromXmlFile_Participants() throws IOException {
        File file = new File("./data/20200109_Sprint_F_.xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(new FileInputStream(file));
        Ski123Data value = xmlMapper.readValue(xml, Ski123Data.class);
        value.getParticipants().forEach(System.out::println);
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