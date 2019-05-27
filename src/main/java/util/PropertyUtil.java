package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final String PROPERTIES_SUFFIX = ".properties";
    private final String propertyName;

    private Properties props;

    public PropertyUtil(String propertyName) {
        this.propertyName = propertyName + PROPERTIES_SUFFIX;
        this.readProperties();
    }

    private void readProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        this.props = new Properties();
        try (InputStream input = loader.getResourceAsStream(this.propertyName)) {
            this.props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return this.props.getProperty(key);
    }

}
