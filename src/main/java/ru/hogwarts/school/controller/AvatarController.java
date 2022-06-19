package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RequestMapping("/student")
@RestController
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }


    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().body("Ok");
    }

    @GetMapping(value = "/{student}/avatar/data")
    public ResponseEntity<byte[]> getAvatarImg(@PathVariable Long student) {
        Avatar avatar = avatarService.findAvatar(student);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        httpHeaders.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatar.getData());
    }

    @GetMapping(value = "/{student}/avatar")
    public void getAvatarImg(@PathVariable Long student, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(student);
        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();
        ) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

}