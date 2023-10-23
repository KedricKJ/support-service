package com.inntri.support.service.Impl;

import com.inntri.support.dto.request.CsvUploadRequest;
import com.inntri.support.dto.response.FileOutput;
import com.inntri.support.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Slf4j
@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

  @Value("${csv.file.path}")
  private String filePath;

  @Override
  public FileOutput getUploadFile(CsvUploadRequest request) {
    FileOutput output = new FileOutput();
    String fileName = filePath + File.separator + request.getOriginalFileName();

    String base64Str = request.getBase64FileString();

    //log.info("base64Str =============================> {}", base64Str);
    //log.info("fileName =============================> {}", fileName);


    if (StringUtils.isBlank(base64Str)) {
      output.setErrorMessage("Please upload a valid csv file!");
      return output;
    } else if (base64Str.contains("data:application/vnd.ms-excel;")) {
      base64Str = base64Str.replace("data:application/vnd.ms-excel;base64,", "");
      //log.info("data:application/vnd.ms-excel 2=============================> {}", base64Str.contains("data:application/vnd.ms-excel;"));
    } else if (base64Str.contains("data:text/csv;")) {
      //log.info("base64Str data:text/csv 1=============================> {}",base64Str.contains("data:text/csv;"));
      base64Str = base64Str.replace("data:text/csv;base64,", "");
    } else if (base64Str.contains("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;")) {
      //log.info("base64Str data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;=============================> {}", base64Str.contains("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"));
      base64Str = base64Str.replace("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,", "");
    } else if (base64Str.contains("data:@file/vnd.openxmlformats-officedocument.spreadsheetml.sheet;")) {
      //log.info("base64Str data:data:@file/vnd.openxmlformats-officedocument.spreadsheetml.sheet;=============================> {}", base64Str.contains("data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"));
      base64Str = base64Str.replace("data:@file/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,", "");
    } else {
      output.setErrorMessage("Please upload a valid csv file!");
      return output;
    }

    String[] stringSpliter = base64Str.split(",");

    //log.info("stringSpliter.length =============================> {}", stringSpliter.length);

    if (stringSpliter.length == 2) {
      base64Str = stringSpliter[1];
    } else {
      base64Str = stringSpliter[0];
    }

    byte[] fileBytes = Base64.getDecoder().decode(base64Str);
    try (FileOutputStream fos = new FileOutputStream(fileName)) {
      fos.write(fileBytes);
    } catch (IOException e) {
      e.printStackTrace();
      output.setErrorMessage("Oops!..Something went wrong with the csv file!");
      return output;
    }
    output.setFile(new File(fileName));
    output.setFileName(fileName);
    output.setValid(true);
    return output;
  }



}
