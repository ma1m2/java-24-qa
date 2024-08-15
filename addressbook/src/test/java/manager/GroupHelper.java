package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase{

  public GroupHelper(AppManager app) {
    super(app);
  }

  public void createGroup(GroupData group) {
    openGroupPage();
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void removeGroup() {
    openGroupPage();
    selectGroup();
    removeSelectedGroup();
    returnToGroupPage();
  }

  public void modifyGroup(GroupData modifiedGroup) {
    openGroupPage();
    selectGroup();
    initGroupModification();
    fillGroupForm(modifiedGroup);
    submitGroupModification();
    returnToGroupPage();
  }

  private void removeSelectedGroup() {
    click(By.name("delete"));
  }

  public void openGroupPage() {
    if (!app.isElementPresent(By.name("new"))) {
      click(By.linkText("groups"));
    }
  }

  private void submitGroupCreation() {
    click(By.name("submit"));
  }

  private void initGroupCreation() {
    click(By.name("new"));
  }

  public boolean isGroupPresent() {
    openGroupPage();
    return app.isElementPresent(By.name("selected[]"));
  }

  private void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  private void submitGroupModification() {
    click(By.name("update"));
  }

  private void fillGroupForm(GroupData group) {
    type(By.name("group_name"), group.name());
    type(By.name("group_header"), group.header());
    type(By.name("group_footer"), group.footer());
  }

  private void initGroupModification() {
    click(By.name("edit"));
  }

  private void selectGroup() {
    click(By.name("selected[]"));
  }

}
