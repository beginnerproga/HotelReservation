package org.mpei.controller.publicApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ImagesPublicController {

    @Operation(summary = "Получение изображения по имени файла",
        description = "Возвращает изображение по заданному имени файла из папки static/images. Если файл не найден или не может быть прочитан, возвращает ошибку.",
        responses = {
            @ApiResponse(description = "Изображение найдено", content = @Content(mediaType = "image/jpeg")),
            @ApiResponse(responseCode = "400", description = "Невозможно прочитать файл изображения")
        })
    @GetMapping("/{imageName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Resource image = new ClassPathResource("static/images/" + imageName);
        if (image.exists() && image.isReadable()) {
            return ResponseEntity.ok().body(image);
        } else {
            log.error("Could not read the image file: {}", imageName);
            throw new RuntimeException("Could not read the image file: " + imageName);
        }
    }
}
