package com.medical.healthcaresystem.controller;

import com.medical.healthcaresystem.entity.MedicalFile;
import com.medical.healthcaresystem.service.LogService;
import com.medical.healthcaresystem.service.MedicalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class MedicalFileController {

    @Autowired
    private MedicalFileService fileService;

    @Autowired
    private LogService logService;

    @PostMapping("/upload/{recordId}")
    public MedicalFile upload(@PathVariable Long recordId,
                              @RequestParam MultipartFile file,
                              @RequestParam String username) throws IOException {

        MedicalFile uploaded = fileService.uploadFile(recordId, file);

        logService.log(username, "Uploaded medical file for record " + recordId);

        return uploaded;
    }
    @GetMapping("/record/{recordId}")
    public List<MedicalFile> getFiles(@PathVariable Long recordId,
                                      @RequestParam String username) {

        logService.log(username, "Viewed files of record " + recordId);

        return fileService.getFilesByRecord(recordId);
    }

}
