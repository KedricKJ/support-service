package com.inntri.support.service;


import com.inntri.support.dto.request.CsvUploadRequest;
import com.inntri.support.dto.response.FileOutput;

public interface FileUploadService {
  FileOutput getUploadFile(CsvUploadRequest request);
}
