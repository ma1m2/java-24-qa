package ru.msl.mantis.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.msl.mantis.model.DevMailUser;
import ru.msl.mantis.model.devmail.AddUserResponse;

import java.io.IOException;
import java.net.CookieManager;

public class DevMailHelper extends HelperBase{
  //video 9.3
  public static final MediaType JSON = MediaType.get("application/json");
  OkHttpClient client;

  public DevMailHelper(AppManager app) {
    super(app);
    client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
  }

  public DevMailUser addUser() {
    RequestBody body = RequestBody.create("", JSON);
    Request request = new Request.Builder()
            .url("https://www.developermail.com/api/v1/mailbox")
            .put(body)
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      var text = response.body().string();
      //System.out.println(text);
      var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);
      if(!addUserResponse.success()) {
        throw new RuntimeException(addUserResponse.errors().toString());
      }
      return addUserResponse.result();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUser(DevMailUser user) {
    RequestBody body = RequestBody.create("", JSON);
    Request request = new Request.Builder()
            .url("https://www.developermail.com/api/v1/mailbox/" + user.name())
            .header("X-MailboxToken", user.token())
            .delete()
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      System.out.println(response.body().string());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
