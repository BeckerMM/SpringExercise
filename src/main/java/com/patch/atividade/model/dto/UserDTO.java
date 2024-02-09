package com.patch.atividade.model.dto;

import com.patch.atividade.model.entity.Archive;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
@Data
public class UserDTO implements IDTO{

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Integer age;
    @NonNull
    private Boolean active;
    private List<Archive> archives;

    public void setArchives(List<MultipartFile> archives)throws IOException {
        if (this.archives == null) this.archives = new ArrayList<>();
        for (MultipartFile archive : archives
        ) {
            Archive a = new Archive();
            a.setData(archive.getBytes());
            a.setName(archive.getOriginalFilename());
            a.setType(archive.getContentType());
            this.archives.add(a);
        }
    }

}
