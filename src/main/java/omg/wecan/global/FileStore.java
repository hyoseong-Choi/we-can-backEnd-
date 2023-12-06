package omg.wecan.global;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStore {
    
    public String getFullPath(String filename) {
//        return "/image/" + filename;
        return "C:\\Users\\gytjd\\" + filename;
    }
    
    public String storeFile(MultipartFile multipartFile) {
        String storeFilePath = getFullPath(createStoreFileName(multipartFile.getOriginalFilename()));
        try {
            multipartFile.transferTo(new File(storeFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return storeFilePath;
    }
    private String createStoreFileName(String originalFilename) {
        return UUID.randomUUID() + "." + extractExt(originalFilename);
    }
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
