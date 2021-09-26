package com.noriteo.delinori.qna.controller;

import com.noriteo.delinori.common.dto.UploadResponseDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {

    @ResponseBody
    @GetMapping("/viewFile")
    public ResponseEntity<byte[]> viewFile(@RequestParam("file") String fileName)throws Exception{

        File file = new File("/Users/hanseul/upload" + File.separator + fileName);

        ResponseEntity<byte[]> result = null;

        byte[] data = FileCopyUtils.copyToByteArray(file);

        String mimeType = Files.probeContentType(file.toPath());

        log.info("mimeType : " + mimeType);

        result = ResponseEntity.ok().header("Content-Type",mimeType).body(data);

        return result;
    }

    @ResponseBody
    @PostMapping("/upload")
    public List<UploadResponseDTO> uploadPost(MultipartFile[] uploadFiles){

        if(uploadFiles != null && uploadFiles.length < 0){
            List<UploadResponseDTO> uploadedList = new ArrayList<>();

            for(MultipartFile multipartFile : uploadFiles){
                try {
                    uploadedList.add(uploadProcess(multipartFile));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return uploadedList;
        }

        return null;
    }

    private UploadResponseDTO uploadProcess(MultipartFile multipartFile) throws Exception{
        String uploadPath = "Users/hanseul/upload";

        String folderName = makeFolder(uploadPath);

        log.info("=================multipartFile================");
        log.info(multipartFile.getContentType());
        log.info(multipartFile.getOriginalFilename());
        log.info(multipartFile.getSize());

        String fileName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String originalFileName = fileName;

        fileName = uuid +"_"+fileName;

        File savedFile = new File(uploadPath + File.separator + folderName , fileName);
        FileCopyUtils.copy(multipartFile.getBytes(),savedFile);

        String mimeType = multipartFile.getContentType();
        boolean checkImage = mimeType.startsWith("image");
        if(checkImage){
            File thumbnailsFile = new File(uploadPath+File.separator + folderName, "s_"+fileName);
            Thumbnailator.createThumbnail(savedFile,thumbnailsFile,100,100);
        }
        return UploadResponseDTO.builder()
                .uuid(uuid)
                .uploadPath(folderName.replace(File.separator,"/"))
                .fileName(originalFileName)
                .image(checkImage)
                .build();
    }

    private String makeFolder(String uploadPath){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        String folderName = str.replace("-", File.separator);
        File uploadFolder = new File(uploadPath, folderName);
        if(uploadFolder.exists() == false){
            uploadFolder.mkdirs();
        }
        return folderName;
    }
}
