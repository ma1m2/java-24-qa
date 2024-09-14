package ru.msl.mantis.manager;

import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.net.CookieManager;

public class HttpSessionHelper extends HelperBase {
  OkHttpClient client;

  public HttpSessionHelper(AppManager app) {
    super(app);
    client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
  }

  public void login(String username, String password) {
    RequestBody formBody = new FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build();
    Request request = new Request.Builder()
            .url(String.format("%s/login.php", app.prop("web.baseUrl")))
            .post(formBody)
            .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      //System.out.println(response.body().string());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isLoggedIn() {
    Request request = new Request.Builder()
            .url(app.prop("web.baseUrl"))
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      String body = response.body().string();
      return body.contains("<span class=\"user-info\">");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
