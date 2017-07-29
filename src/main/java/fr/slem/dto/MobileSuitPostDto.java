package fr.slem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * @author slemoine
 */
public class MobileSuitPostDto implements Serializable {

    @NotNull
    private String modelName;

    @NotNull
    @Size(min = 1)
    private List<String> weapons;

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

        MobileSuitPostDto that = (MobileSuitPostDto) o;

        if (!modelName.equals(that.modelName)) return false;
        return weapons != null ? weapons.equals(that.weapons) : that.weapons == null;

    }

    @Override
    public int hashCode() {
        int result = modelName.hashCode();
        result = 31 * result + (weapons != null ? weapons.hashCode() : 0);
        return result;
    }

}