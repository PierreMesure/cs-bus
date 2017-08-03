/* Pricer Server Software
 *
 * Confidential Property of Pricer AB (publ). Copyright © 1998-2015 Pricer AB (publ),
 * Box 215,Västra Järnvägsgatan 7, SE-101 24 Stockholm, Sweden. All rights reserved.
 */
package service;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerProperties {

  private static ServerProperties instance = null;

  private static final Map<String, Object> DEFAULT_PROPERTIES = new HashMap<String, Object>() {{
    put("trafiklab.api.key", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    put("trafiklab.api.fetchdelay", 1800);
  }};

  protected ServerProperties() {}

  public static ServerProperties getInstance() {
    if(instance == null) {
      instance = new ServerProperties();
    }

    return instance;
  }

  private static Configuration loadFromFile() {

    Configurations configs = new Configurations();

    Configuration config = null;

    File configFile = new File("config.properties");

    if (!configFile.exists() || configFile.isDirectory()) {
      try {
        configFile.createNewFile();

        for (String propertyName : DEFAULT_PROPERTIES.keySet()) {
          saveToFile(propertyName, DEFAULT_PROPERTIES.get(propertyName));
        }
      } catch (IOException | ConfigurationException e) {
        e.printStackTrace();
        System.out.println("No config file found and there was a problem when creating a new one.");
      }
    }

    try {
      config = configs.properties(configFile);
    }
    catch (ConfigurationException e) {
      e.printStackTrace();
    }

    return config;
  }

  private static void saveToFile(String propertyName, Object propertyValue) throws ConfigurationException {
    Parameters params = new Parameters();
    FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
        new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
            .configure(params.properties().setFileName("config.properties")
                           .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));

            Configuration config = builder.getConfiguration();

    config.setProperty(propertyName, propertyValue);
    builder.save();
  }

  public Object getProperty(String name) {
    return loadFromFile().getProperty(name);
  }
}