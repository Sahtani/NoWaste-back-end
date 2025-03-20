package com.youcode.nowastebackend.web;

import com.youcode.nowastebackend.common.controller.GenericController;
import com.youcode.nowastebackend.dto.request.AnnouncementRequestDto;
import com.youcode.nowastebackend.dto.response.AnnouncementResponseDto;
import com.youcode.nowastebackend.service.AnnouncementService;
import com.youcode.nowastebackend.service.Impl.ImageService;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/announcements/")
public class AnnouncementController extends GenericController<AnnouncementRequestDto, AnnouncementResponseDto, Long> {

    private final AnnouncementService  announcementService;
    private final ImageService imageService;
    public AnnouncementController(AnnouncementService announcementService, ImageService imageService) {
        super(announcementService);
        this.announcementService = announcementService;
        this.imageService = imageService;
    }



   @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnnouncementResponseDto> save(@Valid
            @RequestPart("announcement") AnnouncementRequestDto announcementRequest,
            @RequestPart(value = "productImages", required = false) List<MultipartFile> productImages) {

        AnnouncementResponseDto responseDto = announcementService.save(announcementRequest, productImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Resource resource = imageService.loadImageAsResource(fileName);

        try {
            Path filePath = Paths.get("uploads/images", fileName);
            String contentType = Files.probeContentType(filePath);

            return ResponseEntity.ok()
                    .contentType(contentType != null ? MediaType.parseMediaType(contentType) : MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to determine file type", e);
        }
    }
}
