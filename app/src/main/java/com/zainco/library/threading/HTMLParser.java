package com.zainco.library.threading;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {

    public static void main(String[] args) {
        try {
           /* String httpstrings = getHTTPStringsFromWeb("https://instabug.com");
//            System.out.println(httpstrings);
            System.out.println("------------------------------------------------------------------");
            getWordsFromString(httpstrings);*/
            String url = "https://instabug.com";
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text();
            System.out.println(text);
            text = text.replaceAll("[^a-zA-Z0-9]", " ");
            String[] listOfStrings = text.split(" ");
            for (String s : listOfStrings) {
                if (!s.trim().equals(" ") && !s.trim().equals(""))
                    System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getHTTPStringsFromWeb(String urlStr) throws Exception {
        StringBuffer sb = new StringBuffer();
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader br = null;
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
        }
        return sb.toString();
    }

    public static void getWordsFromString(String externalText) throws Exception {
        final String WORD = "\\s\\w\\s";/* \S non white space */
        Pattern pattern = Pattern.compile(WORD);
        Matcher matcher = pattern.matcher(externalText);//указываем свой текст
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("http://www.ssaurel.com/blog").get();
                    String title = doc.title();
                    Elements links = doc.select("a[href]");

                    builder.append(title).append("\n");

                    for (Element link : links) {
                        builder.append("\n").append("Link : ").append(link.attr("href"))
                                .append("\n").append("Text : ").append(link.text());
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }
                System.out.println(builder.toString());

            }
        }).start();
    }
}
