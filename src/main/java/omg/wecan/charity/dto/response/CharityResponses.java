package omg.wecan.charity.dto.response;

import lombok.Getter;
import omg.wecan.charity.entity.Charity;

import java.util.List;

@Getter
public class CharityResponses {
    private final List<CharityResponse> responses;

    public CharityResponses(final List<CharityResponse> responseList) {
        this.responses = responseList;
    }
}
