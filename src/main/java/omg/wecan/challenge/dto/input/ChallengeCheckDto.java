package omg.wecan.challenge.dto.input;


import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.web.multipart.MultipartFile;

        import java.util.ArrayList;
        import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChallengeCheckDto {
    private Long challengeId;
    private List<MultipartFile> images = new ArrayList<>();
}