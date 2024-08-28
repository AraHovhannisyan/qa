package am.fillandgo.endpoints;

import am.fillandgo.services.Impl.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileEndpoint {

    private final FileService fileService;

    @GetMapping
    public byte[] file(@RequestParam("fileName") String fileName) {
        return fileService.downloadFile(fileName);
    }


}
