package omg.wecan.shop.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ItemType {
    EMOTICON, ITEM;
    
    @JsonCreator
    public static ItemType from(String type){
        return ItemType.valueOf(type.toUpperCase());
    }
}
