package omg.wecan.charity.dto.response;

import lombok.Getter;
import omg.wecan.charity.entity.Charity;

import java.util.List;

@Getter
public class CharityResponses {
    private final List<Charity> charityList;

    public CharityResponses(final List<Charity> charityList) {
        this.charityList = charityList;
    }
}
