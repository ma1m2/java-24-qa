package ru.msl.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class AppManager {
  private WebDriver driver;
  private String browser;
  private Properties prop;
  private SessionHelper session;
  private HttpSessionHelper http;
  private JamesCliHelper jamesCli;
  private JamesApiHelper jamesApi;
  private MailHelper mail;
  private UserHelper user;
  private DevMailHelper devMail;
  private RestApiHelper rest;
  private SoapApiHelper soap;

  public void init(String browser, Properties prop) {
    this.prop = prop;
    this.browser = browser;
  }

  public WebDriver driver() {
    if(driver == null) {
      if("chrome".equals(browser)) {
        driver = new ChromeDriver();
      } else if ("firefox".equals(browser)) {
        driver = new FirefoxDriver();
      }else {
        throw new IllegalArgumentException(String.format("Unrecognized browser: %s", browser));
      }
      Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
      driver.get(prop.getProperty("web.baseUrl"));
      driver.manage().window().setSize(new Dimension(1264, 964));
    }
    return driver;
  }

  public SessionHelper session() {
    if(session == null) {
      session = new SessionHelper(this);
    }
    return session;
  }

  public HttpSessionHelper http() {
    if(http == null) {
      http = new HttpSessionHelper(this);
    }
    return http;
  }

  public JamesCliHelper jamesCli() {
    if(jamesCli == null) {
      jamesCli = new JamesCliHelper(this);
    }
    return jamesCli;
  }

  public JamesApiHelper jamesApi() {
    if(jamesApi == null) {
      jamesApi = new JamesApiHelper(this);
    }
    return jamesApi;
  }
  public MailHelper mail() {
    if(mail == null) {
      mail = new MailHelper(this);
    }
    return mail;
  }

  public UserHelper user() {
    if(user == null) {
      user = new UserHelper(this);
    }
    return user;
  }

  public DevMailHelper devMail() {
    if(devMail == null) {
      devMail = new DevMailHelper(this);
    }
    return devMail;
  }

  public RestApiHelper rest() {
    if(rest == null) {
      rest = new RestApiHelper(this);
    }
    return rest;
  }

  public SoapApiHelper soap() {
    if(soap == null) {
      soap = new SoapApiHelper(this);
    }
    return soap;
  }

  public String prop(String key) {
    return prop.getProperty(key);
  }
}
