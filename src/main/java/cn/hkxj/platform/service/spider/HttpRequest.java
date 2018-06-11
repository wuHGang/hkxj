package cn.hkxj.platform.service.spider;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author JR Chan
 * @date 2018/6/4 18:13
 */
public class HttpRequest {
    String url = null;
    Object header = null;
    HttpURLConnection connection = null;


    public HttpRequest(String url) throws IOException {
        this.url = url;
        URL postURL = new URL(this.url);
        connection = (HttpURLConnection) postURL.openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Host", "222.171.107.108");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setRequestProperty("User-Agent", "okhttp/3.3.1");
    }

    public HttpRequest(String url, Object header) throws IOException {
        this(url);
        this.header = header;
    }


    public String get() throws IOException {
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Host","222.171.107.108");
//        connection.setRequestProperty("Connection","Keep-Alive");
//        connection.setRequestProperty("Accept-Encoding","gzip");
//        connection.setRequestProperty("User-Agent","okhttp/3.3.1");
        connection.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes());
            sb.append(lines);
        }
//        System.out.println(sb);
        reader.close();

        connection.disconnect();

        return new String(sb);

    }


    public String post(Object data) throws IOException {
        connection.setRequestMethod("POST");
        connection.setDoInput(true);

        connection.setRequestProperty("Content-Type", "application/json");
        connection.connect();

        Gson gson = new Gson();
        String json = gson.toJson(data);

        OutputStream out = connection.getOutputStream();
        out.write(json.getBytes());
        out.flush();
        out.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String lines;
        StringBuffer sb = new StringBuffer("");
        while ((lines = reader.readLine()) != null) {
            lines = new String(lines.getBytes());
            sb.append(lines);
        }
//        System.out.println(sb);
        reader.close();


        connection.disconnect();

        return new String(sb);
    }
}
