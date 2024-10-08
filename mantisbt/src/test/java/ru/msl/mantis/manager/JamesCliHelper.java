package ru.msl.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class JamesCliHelper extends HelperBase {
  //C:\tools\james-server-jpa-guice>java -Dworking.directory=. -jar james-server-jpa-app.jar
  //C:\tools\james-server-jpa-guice>java -cp "james-server-jpa-app.lib/*" org.apache.james.cli.ServerCmd ListUsers

  public JamesCliHelper(AppManager app) {
    super(app);
  }

  public void addUser(String username, String password) {
    CommandLine cmd = new CommandLine("java", "-cp", "james-server-jpa-app.lib/*",
            "org.apache.james.cli.ServerCmd",
            "AddUser", username, password);
    cmd.setWorkingDirectory(app.prop("james.dir"));
    CircularOutputStream out = new CircularOutputStream();
    cmd.copyOutputTo(out);
    cmd.execute();
    cmd.waitFor();
    System.out.println(out);
  }

  public List<String> listUsers() {
    CommandLine cmd = new CommandLine("java", "-cp", "james-server-jpa-app.lib/*",
            "org.apache.james.cli.ServerCmd",
            "ListUsers");
    cmd.setWorkingDirectory(app.prop("james.dir"));
    CircularOutputStream out = new CircularOutputStream();
    cmd.copyOutputTo(out);
    cmd.execute();
    cmd.waitFor();
    String[] emails = out.toString(Charset.defaultCharset()).split("\n");
    List<String> emailList = Arrays.asList(emails);
    System.out.println(out);
    return emailList;
  }

  public void removeUser(String email) {
    CommandLine cmd = new CommandLine("java", "-cp", "james-server-jpa-app.lib/*",
            "org.apache.james.cli.ServerCmd",
            "RemoveUser", email);
    cmd.setWorkingDirectory(app.prop("james.dir"));
    CircularOutputStream out = new CircularOutputStream();
    cmd.copyOutputTo(out);
    cmd.execute();
    cmd.waitFor();
    System.out.println(out);
  }
}
