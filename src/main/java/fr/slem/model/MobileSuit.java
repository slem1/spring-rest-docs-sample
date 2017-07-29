package fr.slem.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author slemoine
 */
public class MobileSuit implements Serializable {

    private Long id;

    private String modelName;

    private List<String> weapons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<String> weapons) {
        this.weapons = weapons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileSuit that = (MobileSuit) o;

        if (!id.equals(that.id)) return false;
        return modelName.equals(that.modelName);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + modelName.hashCode();
        return result;
    }

}