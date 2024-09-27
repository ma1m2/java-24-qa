package ru.msl.mantis.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeUtility;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.msl.mantis.model.DevMailUser;
import ru.msl.mantis.model.devmail.AddUserResponse;
import ru.msl.mantis.model.devmail.GetIdsResponse;
import ru.msl.mantis.model.devmail.GetMessageResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.CookieManager;
import java.time.Duration;

public class DevMailHelper extends HelperBase {
  //video 9.3
  private static final MediaType JSON = MediaType.get("application/json");
  private OkHttpClient client;

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
      System.out.println(text);
      var addUserResponse = new ObjectMapper().readValue(text, AddUserResponse.class);
      if (!addUserResponse.success()) {
        throw new RuntimeException(addUserResponse.errors().toString());
      }
      return addUserResponse.result();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUser(DevMailUser user) {
    Request request = new Request.Builder()
            .url("https://www.developermail.com/api/v1/mailbox/" + user.name())
            .header("X-MailboxToken", user.token())
            .delete()
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
      System.out.println("Deleted user: " + user.name() + " " + response.body().string());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public String receive(DevMailUser user, Duration duration) {
    var start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + duration.toMillis()) {
      try {
        var text1 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s", user.name()), user.token());
        GetIdsResponse response1 = new ObjectMapper().readValue(text1, GetIdsResponse.class);
        if (!response1.success()) {
          throw new RuntimeException(response1.errors().toString());
        }
        if (response1.result().size() > 0) {
          var text2 = get(String.format("https://www.developermail.com/api/v1/mailbox/%s/messages/%s",
                  user.name(), response1.result().get(0)), user.token());
          var response2 = new ObjectMapper().readValue(text2, GetMessageResponse.class);
          if (!response2.success()) {
            throw new RuntimeException(response2.errors().toString());
          }
          return new String(MimeUtility.decode(
                  new ByteArrayInputStream(response2.result().getBytes()),
                  "quoted-printable").readAllBytes());
        }
      } catch (MessagingException | IOException e) {
        throw new RuntimeException(e);
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

    }
    throw new RuntimeException("No mail");
  }

  public String get(String url, String token) {
    Request request = new Request.Builder()
            .url(url)
            .header("X-MailboxToken", token)
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
      return response.body().string();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}



