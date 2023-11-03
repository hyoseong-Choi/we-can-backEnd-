package omg.wecan.charity.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

public enum CharityCategory {
    ALL, GLOBAL, ELDER, DISABLED_PERSON, MULTIPLE_CULTURE, ENVIRONMENT, ANIMAL, ETC;

    @JsonCreator
    public static CharityCategory from(String s){
        return CharityCategory.valueOf(s.toUpperCase());
    }
}
