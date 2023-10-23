package com.shiropure.campuslink.controller;

import com.shiropure.campuslink.entity.File;
import com.shiropure.campuslink.entity.Log;
import com.shiropure.campuslink.entity.LogLevel;
import com.shiropure.campuslink.repository.FileRepository;
import com.shiropure.campuslink.repository.LogRepository;
import com.shiropure.campuslink.repository.TokenRepository;
import com.shiropure.campuslink.utils.ApiResponseObject;
import com.shiropure.campuslink.utils.DateTool;
import com.shiropure.campuslink.utils.IPTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@CrossOrigin(origins = "*")
@Api(value = "upload Controller", description = "handle file upload")
public class FileUploadController {
    @Value("${my.host}")
    private String host;

    @Autowired
    private TokenRepository tokenRepo;

    @Autowired
    private LogRepository logRepo;

    @Autowired
    private FileRepository fileRepo;

    @Value("${upload.path}")
    private String uploadPath;


    @PostMapping("/upload")
    @CrossOrigin
    @ApiOperation(value = "upload file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "upload successful"),
            @ApiResponse(code = 400, message = "Missing fields"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<ApiResponseObject> uploadFile(@RequestParam("file") MultipartFile file,
                                                        HttpServletRequest request) {
        Log log = new Log(LogLevel.INFO, "upload", new Date(), IPTool.getIP(request));
        ApiResponseObject response = new ApiResponseObject();

        if(file.isEmpty())
        {
            log.setAdditionalInfo("upload failed, file is empty");
            logRepo.save(log);

            response.setCode(400);
            response.setMsg("file is empty");
            return ResponseEntity.status(400).body(response);
        }
        try {
            byte[] bytes = file.getBytes();
            String timeStamp = DateTool.getNowInString(new SimpleDateFormat("yyyyMMddHHmmss-"));
            Path path = Paths.get(uploadPath + "/uploads/" + timeStamp + file.getOriginalFilename());
            Files.write(path, bytes);

            String fileUrl = host + "/uploads/" + timeStamp+ file.getOriginalFilename();
            File fileEntity = new File(file.getOriginalFilename(), fileUrl);
            fileRepo.save(fileEntity);

            response.setCode(200);
            response.setMsg("upload successful");
            response.insertData("fileUrl", fileUrl);

            log.setAdditionalInfo("upload successful");
            logRepo.save(log);
            return ResponseEntity.ok().body(response);

        } catch (IOException e) {
            log.setLogLevel(LogLevel.ERROR);
            log.setAdditionalInfo("upload failed, internal error");
            logRepo.save(log);
            System.out.println(e.getMessage());


            response.setCode(500);
            response.setMsg("internal error");
            return ResponseEntity.status(500).body(response);
        }
    }
}
