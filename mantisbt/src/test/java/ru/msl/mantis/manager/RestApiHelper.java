package ru.msl.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.msl.mantis.model.IssueData;
import ru.msl.mantis.model.UserData;

public class RestApiHelper extends HelperBase{

  public RestApiHelper(AppManager app) {
    super(app);
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
    Authorization.setApiKey(app.prop("apiKey"));
  }

  public void createIssue(IssueData issueData) {
    Issue issue = new Issue(); // Issue | The issue to add.
    issue.summary(issueData.summary());
    issue.description(issueData.description());
    var projectId = new Identifier();
    projectId.setId(issueData.project());
    issue.setProject(projectId);
    var categoryId = new Identifier();
    categoryId.setId(issueData.category());
    issue.setCategory(categoryId);

    IssuesApi apiInstance = new IssuesApi();

    try {
      apiInstance.issueAdd(issue);
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }
  }

  public void addUser(UserData userData) {
    UserApi apiInstance = new UserApi();
    User user = new User(); // User | The user to add.
    user.setUsername(userData.username());
    user.setRealName(userData.realName());
    user.setEmail(userData.email());
    user.setPassword(userData.password());
    try {
      UserAddResponse result = apiInstance.userAdd(user);
      System.out.println(result);
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }
  }
}
