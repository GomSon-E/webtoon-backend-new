package com.erp.webtoon.service;

import com.erp.webtoon.domain.*;
import com.erp.webtoon.dto.plas.*;
import com.erp.webtoon.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlasService {

    private final DocumentRepository documentRepository;
    private final DocumentTplRepository documentTplRepository;
    private final DocumentRcvRepository documentRcvRepository;
    private final DocumentDataRepository documentDataRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    /*
        템플릿 조회
    */
    @Transactional(readOnly = true)
    public List<DocTplListDto> getTemplateList() {
        List<DocumentTpl> templateList = documentTplRepository.findAll();

        return templateList.stream()
                .map(template -> DocTplListDto.builder()
                        .id(template.getId())
                        .templateName(template.getTemplateName())
                        .intro(template.getIntro())
                        .template(template.getTemplate())
                        .build())
                .collect(Collectors.toList());
    }

    /*
        결재자 / 참조자 조회
    */
    @Transactional(readOnly = true)
    public List<AppvLineListDto> getAppvLineList() {
        List<User> appvLineList = userRepository.findAll();

        return appvLineList.stream()
                .map(user -> AppvLineListDto.builder()
                        .name(user.getName())
                        .deptName(user.getDeptName())
                        .teamNum(user.getTeamNum())
                        .position(user.getPosition())
                        .build())
                .collect(Collectors.toList());
    }

    /*
        내 문서 조회
    */
    @Transactional(readOnly = true)
    public List<DocListDto> getMyDocList(String employeeId) {
        User writeUser = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("문서 작성 직원의 정보가 존재하지 않습니다."));

        List<Document> myDocList = documentRepository.findAllByWriteUser(writeUser);

        return docStreamToList(myDocList);

    }

    /*
        내 부서 문서 조회
    */
    @Transactional(readOnly = true)
    public List<DocListDto> getMyDeptDocList(String deptCode) {

        List<User> myDeptUserList = userRepository.findAllByDeptCode(deptCode);

        List<Document> myDeptDocList = documentRepository.findAllByWriteUserIn(myDeptUserList);

        return docStreamToList(myDeptDocList);
    }

    /*
        내 결재대기 & 참조 문서 조회
    */
    @Transactional(readOnly = true)
    public List<DocListDto> getMyAppvOrCCDocList(String rcvType, String employeeId) {

        User appvUser = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("사용자 정보가 존재하지 않습니다."));

        List<DocumentRcv> myDocumentRcvList = documentRcvRepository.findAllByUserAndReceiveTypeAndStat(appvUser, rcvType, true);

        List<Document> myAppvDocList = myDocumentRcvList.stream()
                                            .map(DocumentRcv::getDocument).collect(Collectors.toList());

        return docStreamToList(myAppvDocList);
    }

    public List<DocListDto> docStreamToList(List<Document> documentList) {
        return documentList.stream()
                .map(doc -> DocListDto.builder()
                        .templateName(doc.getDocumentTpl().getTemplateName())
                        .title(doc.getTitle())
                        .reg_date(doc.getRegDate())
                        .writeDeptName(doc.getWriteUser().getDeptName())
                        .writeUsername(doc.getWriteUser().getUsername())
                        .documentRcvNames(doc.getRcvNames())
                        .stat(doc.getStat())
                        .build())
                .collect(Collectors.toList());
    }

    public void addDoc(DocumentRequestDto dto) throws IOException {
        DocumentTpl documentTpl = documentTplRepository.findById(dto.getDocumentTpId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 문서 템플릿 정보입니다."));

        User writeUser = userRepository.findByEmployeeId(dto.getWriteEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 작성자 정보입니다."));

        Document document = dto.toEntity(documentTpl, writeUser);

        // 문서 DATA 저장
        if (!dto.getDocumentDataRequests().isEmpty()) {
            List<DocumentData> documentDataList = dto.getDocumentDataRequests().stream()
                    .map(dataRequestDto -> dataRequestDto.toEntity(document))
                    .collect(Collectors.toList());
            documentDataRepository.saveAll(documentDataList);
        }

        // 문서 수신자 저장
        if (!dto.getDocumentRcvRequests().isEmpty()) {
            List<DocumentRcv> documentRcvList = dto.getDocumentRcvRequests().stream()
                    .map(rcvRequestDto -> {
                        User rcvUser = userRepository.findByEmployeeId(rcvRequestDto.getRcvEmployeeId())
                                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 결재자/참조자 정보입니다."));

                        return rcvRequestDto.toEntity(document, rcvUser);
                    })
                    .collect(Collectors.toList());
            documentRcvRepository.saveAll(documentRcvList);
        }

        // 파일 저장
        if (!dto.getUploadFiles().isEmpty()) {
            for (MultipartFile file: dto.getUploadFiles()) {
                File saveFile = fileService.save(file);
                saveFile.updateFileDocument(document);
                document.getFiles().add(saveFile);
            }
        }

        // 문서 저장
        documentRepository.save(document);
    }
}
