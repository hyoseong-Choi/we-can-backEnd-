package omg.wecan.challenge.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChallengeCheckImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private ChallengeCheck challengeCheck;

    private String originName;

    private String storedName;

    private String imageUrl;

    public ChallengeCheckImage(ChallengeCheck challengeCheck, String originName) {
        this.challengeCheck = challengeCheck;
        this.originName = originName;
        this.storedName = getFileName(originName);
        this.imageUrl = "";
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // 이미지 파일의 확장자 추출 메소드
    public String extractExtension(String originName) {
        int index = originName.lastIndexOf('.');

        return originName.substring(index, originName.length());
    }


    public String getFileName(String originName) {
        return UUID.randomUUID() + "." + extractExtension(originName);
    }
}