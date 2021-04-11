package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final Random random = new Random();
    public static String PICTURE_ID1 = "uT-o3vU3bSo";
    public static String PICTURE_ID2 = "1_dNimR66kw";
    public static String PICTURE_ID3 = "mo0vJht18NY";
    public static String PICTURE_ID4 = "tbDbaotEEiE";
    public static String PICTURE_ID5 = "AbNO2iejoXA";
    public static String PICTURE_ID6 = "vNAZubsDWMg";
    public static String PICTURE_ID7 = "V3oKqioVnpc";
    public static String PICTURE_ID8 = "PzAmR_Nt7KM";

    private static List<String> readAllLines(String resource) throws IOException {
        //Soubory z resources se získávají pomocí classloaderu. Nejprve musíme získat aktuální classloader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //Pomocí metody getResourceAsStream() získáme z classloaderu InpuStream, který čte z příslušného souboru.
        //Následně InputStream převedeme na BufferedRead, který čte text v kódování UTF-8
        try (InputStream inputStream = classLoader.getResourceAsStream(resource);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            //Metoda lines() vrací stream řádků ze souboru. Pomocí kolektoru převedeme Stream<String> na List<String>.
            return reader.lines().collect(Collectors.toList());
        }
    }

    @GetMapping("/")
    public ModelAndView getDynamicPage() throws IOException {
        ModelAndView result = new ModelAndView("dynamicpage");//name of index.html file like datum.html
        List<String> pictures = new ArrayList<>();
        List<String> seznamTextu = readAllLines("citaty.txt");
        int nahodneCislo = random.nextInt(seznamTextu.size());
        pictures.add(PICTURE_ID1);
        pictures.add(PICTURE_ID2);
        pictures.add(PICTURE_ID3);
        pictures.add(PICTURE_ID4);
        pictures.add(PICTURE_ID5);
        pictures.add(PICTURE_ID6);
        pictures.add(PICTURE_ID7);
        pictures.add(PICTURE_ID8);

        result.addObject("citat", seznamTextu.get(nahodneCislo));
        result.addObject("pictures", String.format("background-image: url(https://source.unsplash.com/%s/1600x900)", pictures.get(nahodneCislo)));


        return result;
    }


}

