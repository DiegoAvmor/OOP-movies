package moviewebservice.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import java.io.IOException;
import java.net.URL;

public class JsonPathUtil {

    private static Configuration configuration;

    private static Configuration buildConfiguration() {
        return  Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();
    }

    private static Configuration initConfiguration() {
        if(configuration == null)
            configuration = buildConfiguration();
        return configuration;
    }


    public static <T> T getMappedEntities(URL jsonURL, String jsonPath, TypeRef<T> typeRef) {
        initConfiguration();

        T returnValue = null;

        try {
            returnValue = JsonPath
                    .using(configuration)
                    .parse(jsonURL)
                    .read(jsonPath, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
