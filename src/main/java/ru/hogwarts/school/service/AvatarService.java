package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.apache.commons.io.FilenameUtils.getExtension;

@Service
@Transactional
public class AvatarService {

    @Value("${avatar.dir.path}")
    private String avatarDir;

    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }


    public void uploadAvatar(Long studentId, MultipartFile avatarIn) throws IOException {


        Student student = studentService.getStudentId(studentId);
        Path avatarOut = Path.of(avatarDir, studentId + "." + getExtension(avatarIn.getOriginalFilename()));

        Files.createDirectories(avatarOut.getParent());

        Files.deleteIfExists(avatarOut);
        try (InputStream is = avatarIn.getInputStream();
             OutputStream os = Files.newOutputStream(avatarOut, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }//try

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(avatarOut.toString());
        avatar.setFileSize(avatarIn.getSize());
        avatar.setMediaType(avatarIn.getContentType());
        avatar.setData(dataImage(avatarOut));
        avatarRepository.save(avatar);

    }

    private byte[] dataImage(Path avatarOut) throws IOException {
        try (

                InputStream is = Files.newInputStream(avatarOut);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            //           File file = new File(String.valueOf(avatarOut));
            //           BufferedImage image = ImageIO.read(file);

            BufferedImage imgIn = ImageIO.read(bis);
            double height = imgIn.getHeight() / (imgIn.getWidth() / 100d);
            BufferedImage imgOut = new BufferedImage(100, (int) height, imgIn.getType());
            Graphics2D graphics = imgOut.createGraphics();
            graphics.drawImage((Image) imgIn, 0, 0, 100, (int) height, null);
            graphics.dispose();

            ImageIO.write(imgOut, getExtension(avatarOut.getFileName().toString()), baos);
            return baos.toByteArray();


        }
    }

    public Avatar findAvatar(Long id) {
        return avatarRepository.findByStudentId(id).orElse(new Avatar());
    }
}
