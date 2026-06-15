package com.fundoonotesapp.fundoo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentResponse {

    private Long id;

    private String originalFileName;

    private String fileType;

    private Long fileSize;
}