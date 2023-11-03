package omg.wecan.charity.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.entity.CharityCategory;

@Getter
public class CharityCreateRequest {
    @NotNull(message = "null일 수 없습니다.")
    private String name;

    //JSON 문자열에서 ENUM으로 파싱이 잘 될까?
    //기본값 설정
    private CharityCategory category = CharityCategory.ALL;

    private String explanation;

    private String imgEndpoint;

    public Charity toEntity(){
        return Charity.builder()
                .name(name)
                .category(category)
                .explanation(explanation)
                .imgEndpoint(imgEndpoint)
                .build();
    }
}
