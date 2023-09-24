package com.emigm.price.shared.infrastructure.config;


import com.emigm.price.shared.domain.Injectable;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Injectable
public class Parameter {

    private final Properties properties = new Properties();

    public Parameter() {
        try {
            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("application.yml");
            properties.load(inputStream);
            inputStream.close();
        } catch (Exception exception) {
            // TODO: revisar los proyectos sin YML antes de desplegar las libs compartidas throw new RuntimeException(exception);
        }
    }

    public String get(String key) throws ParameterNotExist {

        String value = Objects.nonNull(System.getenv(key)) ? System.getenv(key) : properties.getProperty(key);

        if (Objects.equals(value, key)) {
            throw new ParameterNotExist(key);
        }

        return value;
    }

    public Integer getInt(String key) throws ParameterNotExist {
        String value = get(key);

        return Integer.parseInt(value);
    }
}
