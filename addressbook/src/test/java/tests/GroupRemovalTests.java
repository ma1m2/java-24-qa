package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

  @Test
  public void canRemoveGroup() {
    if(!app.group().isGroupPresent()) {
      app.group().createGroup(new GroupData("group name", "group header", "group footer"));
    }
    app.group().removeGroup();
  }

}
