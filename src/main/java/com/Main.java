package com;

import com.configuration.GeneratorDataConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Slf4j
@Component
public class Main {

  public static void main(String[] args) {

    try {
      SwingUtilities.invokeAndWait(() -> {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(GeneratorDataConfig.class);
        GeneratorDataConfig config = (GeneratorDataConfig)ctx.getBean("config");

        new MainPanel(config);
      });
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
  }
}
