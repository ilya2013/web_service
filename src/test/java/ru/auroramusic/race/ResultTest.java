package ru.auroramusic.race;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import ru.auroramusic.race.data.Result;

import java.io.*;

import static org.junit.Assert.*;

public class ResultTest {

    @Test
    public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(new Result());
        System.out.println(xml);
        assertNotNull(xml);
    }



//    @Test
//    public void whenJavaGotFromXmlStr_thenCorrect() throws IOException {
//        XmlMapper xmlMapper = new XmlMapper();
//        Result object
//                = xmlMapper.readValue("<Result><id>1</id><dtFinish>11:26:33.235</dtFinish></Result>", Result.class);
//        assertTrue(object.getId() == (1) && object.getDtFinish().equals("11:26:33.235"));
//    }

//    @Test
//    public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
//        File file = new File("./data/20200109_Sprint_F_.xml");
//        XmlMapper xmlMapper = new XmlMapper();
//        String xml = inputStreamToString(new FileInputStream(file));
//        Result value = xmlMapper.readValue(xml, Result.class);
//        //assertTrue(value.getX() == 1 && value.getY() == 2);
//    }

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