package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.Util.randomFile;
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

  public static void main(String[] args) throws IOException {
    var generator = new Generator();
    JCommander.newBuilder()
            .addObject(generator)
            .build()
            .parse(args);
    generator.run();
  }

  private void run() throws IOException {
    var data = generate();
    save(data);
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

  private void save(Object data) throws IOException {
    if ("json".equals(format)) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.writeValue(new File(output), data);
    }else if("yaml".equals(format)) {
      var mapper = new YAMLMapper();
      mapper.writeValue(new File(output), data);
    }else if("xml".equals(format)) {
      var mapper = new XmlMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      mapper.writeValue(new File(output), data);
    } else {
      throw new IllegalArgumentException("Unrecognized format: " + format);
    }
  }

  private void save2(Object data) throws IOException {
    if ("json".equals(format)) {
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      var json = mapper.writeValueAsString(data);

      try(FileWriter writer = new FileWriter(output)) {
        writer.write(json);
      }

    }else {
      throw new IllegalArgumentException("Unrecognized format: " + format);
    }
  }

  private Object generateData(Supplier<Object> dataSupplier) {
    return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
  }

  private Object generateGroups() {
    return generateData(() -> new GroupData()
            .withName(randomString(8))
            .withHeader(randomString(8))
            .withFooter(randomString(8)));
  }

  private Object generateContacts() {
    return generateData(() -> new ContactData()
            .withFirstName(randomString(8))
            .withLastName(randomString(8))
            .withPhoto(randomFile("src/test/resources/images")));
  }

  private Object generateGroupsOld() {
    var result = new ArrayList<GroupData>();
    for (int i = 0; i < count; i++) {
      result.add(new GroupData()
              .withName(randomString(i*5))
              .withHeader(randomString(i*5))
              .withFooter(randomString(i*5)));
    }
    return result;
  }

  private Object generateContactsOld() {
    var result = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      result.add(new ContactData()
              .withFirstName(randomString(i*5))
              .withLastName(randomString(i*5))
              .withPhoto(randomFile("src/test/resources/images")));
    }
    return result;
  }

  private Object generateAll() {
    return null;
  }
}
