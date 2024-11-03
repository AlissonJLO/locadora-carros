package br.mt.cba.ufmt.ic.si.alg3.config;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {

  private LoggerConfig() {
    throw new IllegalStateException("Utility class");
  }

  public static Logger getLogger(Class<?> clazz) {
    Logger logger = Logger.getLogger(clazz.getName());
    ConsoleHandler consoleHandler = new ConsoleHandler();
    try {
      consoleHandler.setEncoding(StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      logger.warning("Encoding não suportado: " + e.getMessage());
    }
    consoleHandler.setFormatter(new SimpleFormatter());

    logger.setUseParentHandlers(false); // Desativa handlers padrão para evitar duplicação
    logger.addHandler(consoleHandler); // Adiciona o handler com UTF-8

    return logger;
  }
}
