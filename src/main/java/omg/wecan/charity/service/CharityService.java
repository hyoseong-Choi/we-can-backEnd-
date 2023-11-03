package omg.wecan.charity.service;

import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.entity.Charity;
import omg.wecan.charity.entity.CharityCategory;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import omg.wecan.charity.repository.CharityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CharityService {
    private final CharityRepository charityRepository;

    //1. 기부 단체 등록
    public CharityResponse save(CharityCreateRequest request){
        Charity charity = request.toEntity();
        return new CharityResponse(charityRepository.save(charity));
    }

    //2. 기부 단체 전체 조회
    //4. 기부 단체 카테고리별 조회
    public CharityResponses findAllByCategory(CharityCategory category){
        List<Charity> charities;

        if(category.equals(CharityCategory.ALL))
            charities = charityRepository.findAll();
        else
            charities = charityRepository.findAllByCategory(category);

        return new CharityResponses(charities);
    }

    //4. 기부 단체 카테고리별 + 검색 입력
    public CharityResponses findAllByCategoryAndExplanation(CharityCategory category, String explanation){
        List<Charity> charities;

        if(category.equals(CharityCategory.ALL))
            charities = charityRepository.findAllByExplanationLike("%" + explanation + "%");
        else
            charities = charityRepository.findAllByCategoryAndExplanationIsLike(category, "%" + explanation + "%");

        return new CharityResponses(charities);
    }

    //5. 기부 단체 상세 조회

    //6. 기부 단체 정보 수정

    //7. 기부 단체 정보 삭제


}
