import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void canCreateGroup(){
    openGroupPage();
    createGroup(new GroupData("group name", "group header", "group footer"));
  }

  @Test
  public void canCreateGroupWithEmptyFields() {
    openGroupPage();
    createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnly() {
    openGroupPage();
    createGroup(new GroupData().withName("some group"));
  }

}
