package ru.msl.mantis.manager;

import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.util.List;

public class JamesApiHelper extends HelperBase {
  //video 9.2
  public static final MediaType JSON = MediaType.get("application/json");
  OkHttpClient client;

  public JamesApiHelper(AppManager app) {
    super(app);
    client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
  }

  public void addUser(String email, String password) {
    var json = String.format("{\"password\":\"%s\"}", password);
    RequestBody body = RequestBody.create(json, JSON);
    Request request = new Request.Builder()
            .url(String.format("%s/users/%s", app.prop("james.apiBaseUrl"), email))
            .put(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      //System.out.println(response.body().string());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String listUsers() {
    Request request = new Request.Builder()
            .url(String.format("%s/users", app.prop("james.apiBaseUrl")))
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      return response.body().string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void removeUser(String email) {
    Request request = new Request.Builder()
            .url(String.format("%s/users/%s", app.prop("james.apiBaseUrl"), email))
            .delete()
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      System.out.println("Status code is: " + response.code());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
