package moviewebservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * <h>Clase que anotada como entitad que es mapeado de forma directa a la tabla
 * genres ubicada en la base de datos. Consiste unicamente de dos atributos y sus
 * sets and gets correspondientes.
 * @author diego
 */
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    private Integer id;
    private String name;
//------------Sets and Gets-------------------
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
