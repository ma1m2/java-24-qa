package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

  @Test
  public void canModifyGroup() {
    if(app.group().getCount() == 0) {
      app.group().createGroup(new GroupData("", "group name", "group header", "group footer"));
    }
    app.group().modifyGroup(new GroupData().withName("modified group"));
  }

}
