package ru.hogwarts.school.controller;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/avatar")
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
    public ResponseEntity<byte[]> getAvatarImg(@PathVariable Long student) throws IOException {
        Avatar avatar = avatarService.findByStudentId(student);
        if (avatar == null) throw new NotFoundException("Не найден студент с таким кодом");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        httpHeaders.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatar.getData());
    }


    @GetMapping(value = "/{student}/avatar")
    public void getAvatarImg(@PathVariable Long student, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(student).orElseThrow(() -> new NotFoundException("Студент не найден"));

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();
        ) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/avatar/all")
    public ResponseEntity<List<Avatar>> getAvatarImgList(@RequestParam("page") Integer pageNumber,
                                                         @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = new ArrayList<Avatar>(avatarService.findAll(pageNumber, pageSize));

        return ResponseEntity.status(HttpStatus.OK).body(avatars);
    }


}