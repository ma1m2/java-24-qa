package ru.msl.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.msl.mantis.common.Util;
import ru.msl.mantis.model.IssueData;

public class IssueCreationTests extends TestBase {

  @Test
  public void canCreateIssueRest() {
    app.rest().createIssue(new IssueData()
            .withSummary(Util.randomString(10))
            .withDescription(Util.randomString(50))
            .withProject(1L));
  }


  @Test
  public void canCreateIssueSoap() {
    app.soap().createIssue(new IssueData()
            .withSummary(Util.randomString(13))
            .withDescription(Util.randomString(40))
            .withProject(1L));
  }
}
