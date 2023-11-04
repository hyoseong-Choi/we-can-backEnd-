package omg.wecan.charity.dto.request;

import lombok.Getter;
import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.entity.CharityCategory;

@Getter
public class CharityUpdateRequest {
    private String name;

    private CharityCategory category;

    private String explanation;

    private String imgEndPoint;

    private String pageEndpoint;
}
