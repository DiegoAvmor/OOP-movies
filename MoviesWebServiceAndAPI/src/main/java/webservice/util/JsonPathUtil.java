package webservice.util;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import java.io.IOException;
import java.net.URL;

/**
 * Esta clase configura la herramienta JsonPath y proporciona métodos
 * que permiten hacer uso de la herramienta.
 */
public class JsonPathUtil {

    /**
     * Almacena la configuración de la herramienta.
     */
    private static Configuration configuration;

    /**
     * Crea un objeto {@link Configuration} para
     * configurar JsonPath. Jackson está establecido
     * como el proveedor de mapeo JSON.
     *
     * @return un objeto {@link Configuration}
     */
    private static Configuration buildConfiguration() {
        return  Configuration
                .builder()
                .mappingProvider(new JacksonMappingProvider())
                .jsonProvider(new JacksonJsonProvider())
                .build();
    }

    /**
     *  Inicializa la configuración.
     *
     * @return
     */
    private static Configuration initConfiguration() {
        if(configuration == null)
            configuration = buildConfiguration();
        return configuration;
    }


    /**
     * Esconde la implementación de JsonPath para obtener una estructura
     * de JSON especificada.
     *
     * @param jsonURL es la ruta del JSON.
     * @param jsonPath especifica qué partes del JSON y cómo se desean recuperar.
     * @param typeRef el JSON es mapeado al tipo de datos especificado aquí.
     * @param <T>
     * @return
     */
    @SuppressWarnings("deprecation")
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
