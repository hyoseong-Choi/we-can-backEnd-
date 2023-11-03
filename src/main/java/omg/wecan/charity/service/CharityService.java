package omg.wecan.charity.service;

import omg.wecan.charity.dto.request.CharityCreateRequest;
import omg.wecan.charity.dto.response.CharityResponse;
import omg.wecan.charity.dto.response.CharityResponses;
import omg.wecan.charity.entity.Charity;
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
    public CharityResponses findAll(){
        List<Charity> charityList = charityRepository.findAll();
        return new CharityResponses(charityList);
    }

    //3. 기부 단체 검색창 조회

    //4. 기부 단체 카테고리별 조회

    //5. 기부 단체 상세 조회

    //6. 기부 단체 정보 수정

    //7. 기부 단체 정보 삭제


}
