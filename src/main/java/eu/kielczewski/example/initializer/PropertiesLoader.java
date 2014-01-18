package eu.kielczewski.example.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesLoader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    @SuppressWarnings("SameParameterValue")
    public Properties load(String fileName) {
        Properties prop = new Properties();
        InputStream im = null;
        try {
            im = findFile(fileName);
            prop.load(im);
        } catch (IOException ignore) {
        } finally {
            if (im != null) {
                try {
                    im.close();
                } catch (IOException ignore) {
                }
            }
        }
        return prop;
    }

    private InputStream findFile(String fileName) throws FileNotFoundException {
        InputStream im = findInWorkingDirectory(fileName);
        if (im == null) im = findInClasspath(fileName);
        if (im == null) im = findInSourceDirectory(fileName);
        return im;
    }

    private InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
        logger.debug("Trying {} in source directory", fileName);
        return new FileInputStream("src/main/resources/" + fileName);
    }

    private InputStream findInClasspath(String fileName) {
        logger.debug("Trying {} on the classpath", fileName);
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    private InputStream findInWorkingDirectory(String fileName) {
        try {
            logger.debug("Trying {} in current directory", fileName);
            return new FileInputStream(System.getProperty("user.dir") + fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
