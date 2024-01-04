package com.erp.webtoon.dto.plas;

import com.erp.webtoon.domain.Document;
import com.erp.webtoon.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private String templateName;

    @NotBlank
    private String writeEmployeeId; // 작성자ID

    private List<DocumentRcvRequestDto> documentRcvRequests;

    private List<DocumentDataDto> documentDataRequests;

    public Document toEntity(User writeUser) {
        return Document.builder()
                .title(title)
                .content(content)
                .stat('N')
                .templateName(templateName)
                .writeUser(writeUser)
                .build();
    }
}
