package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import model.GroupData;

import java.util.ArrayList;

import static common.Util.randomString;

public class Generator {
  @Parameter(names = {"--type", "-t"})
  private String type;
  @Parameter(names = {"--output", "-o"})
  private String output;
  @Parameter(names = {"--format", "-f"})
  private String format;
  @Parameter(names = {"--count", "-c"})
  private int count;

  public static void main(String[] args) {
    var generator = new Generator();
    JCommander.newBuilder()
            .addObject(generator)
            .build()
            .parse(args);
    generator.run();
  }

  private void run() {
    var data = generate();
    save(data);
  }

  private void save(Object data) {

  }

  private Object generate() {
    if ("groups".equals(type)) {
      return generateGroups();
    } else if ("contacts".equals(type)) {
      return generateContacts();
    } else if ("all".equals(type)) {
      return generateAll();
    } else {
      throw new IllegalArgumentException("Unrecognized type: " + type);
    }
  }

  private Object generateAll() {
    return null;
  }

  private Object generateContacts() {
    return null;
  }

  private Object generateGroups() {
    var result = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      result.add(new GroupData()
              .withName(randomString(i+5))
              .withHeader(randomString(i+5))
              .withFooter(randomString(i+5)));
    }
    return result;
  }

}
