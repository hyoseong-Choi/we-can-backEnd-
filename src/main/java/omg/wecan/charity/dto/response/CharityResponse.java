package omg.wecan.charity.dto.response;

import lombok.Getter;
import omg.wecan.charity.entity.Charity;

@Getter
public class CharityResponse {
    private final Long id;

    private final String name;

    private final String category;

    private final String explanation;

    private final String imgEndpoint;

    public CharityResponse(final Charity charity){
        id = charity.getId();
        name = charity.getName();
        category = charity.getCategory().toString();
        explanation = charity.getExplanation();
        imgEndpoint = charity.getImgEndpoint();
    }
}
