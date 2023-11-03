package omg.wecan.charity.service;

import lombok.RequiredArgsConstructor;
import omg.wecan.charity.repository.CharityRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharityService {
    private final CharityRepository charityRepository;


}
