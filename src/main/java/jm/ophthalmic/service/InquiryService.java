package jm.ophthalmic.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jm.ophthalmic.domain.Inquiry;
import jm.ophthalmic.repository.InquiryRepository;

public class InquiryService {
    
    private InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }
    public Long register(Inquiry inquiry){
        return inquiryRepository.save(inquiry).getInquiry_id();
    }
    public List<Inquiry> findInquiries(){
        return inquiryRepository.findAll();
    }
    public Optional<Inquiry> findOnebyId(Long review_id){
        return inquiryRepository.findbyId(review_id);
    }
    public List<Inquiry> findOnebyUserId(Long id){
        return inquiryRepository.findbyUserId(id);
    }
    public Inquiry saveImageDetail(Inquiry inquiry){
        if(inquiry.getInquiry_image() instanceof MultipartFile mf){
            //원본파일의 이름
            String fileName = StringUtils.cleanPath(mf.getOriginalFilename());
            //파일 이름에 추가할 inquiry의 id를 저장소 크기+1로 미리 조회
            long id = (inquiryRepository.storageSize()+1);
            Path path = Paths.get("src/main/resources/static/upload/inquiry/"+id+"_"+fileName);
            try{ 
                //copy 메서드의 replace 옵션으로 중복저장을 허용해주고 복사
                Files.copy(mf.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                //복사가 정상 완료되면 디테일 정보를 객체에 세팅
                inquiry.setInquiry_imageName(fileName);
                inquiry.setInquiry_imagePath("/upload/inquiry/"+id+"_"+fileName);
            }catch(IOException e){
                System.out.println("이미지 저장에 실패했습니다.");
            }
            return inquiry;
        }else{
            return null;
        }
    }
}
