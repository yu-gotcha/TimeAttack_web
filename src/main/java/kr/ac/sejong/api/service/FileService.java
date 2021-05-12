package kr.ac.sejong.api.service;

import kr.ac.sejong.api.domain.Upload;
import kr.ac.sejong.api.domain.User;
import kr.ac.sejong.api.repository.UploadRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class FileService {
    private final UploadRepository uploadRepository;

    public FileService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }



    public List<Map<String, Object>> getFileListByUser(User user){
        long no=1;
        List<Upload> uploadList2 = uploadRepository.findByUser(user);

        //List<Upload> uploadList2=user.getUploadList();

        System.out.println(uploadList2.size());

        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();

        String uploading, processing;

        for(Upload i : uploadList2){
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("no", no++);
            //이름 가져올 수 있어야함
            map.put("imageFileName", i.getUploadImg().getUpImgName());
            map.put("videoFileName", i.getUploadVid().getUpVidName());

            if(i.getUploading()==1) uploading = "upload completed";
            else uploading = "uploading...";
            map.put("upload", uploading);

            if(i.getProcessing()==1) processing = "process completed";
            else processing = "processing...";
            map.put("process", processing);

            map.put("imgSavedName", i.getUploadImg().getUpImgSavedName());
            map.put("vidSavedName", i.getUploadVid().getUpVidSavedName());

            fileList.add(map);
        }

        /*
        System.out.println(fileList);
        System.out.println("-----------------------------------------------------------");

        System.out.println("count--"+ uploadRepository.count());
        System.out.println("count by user--"+ uploadRepository.countByUser(user));
        System.out.println("-----------------------------------------------------------");
        */

        return fileList;
    }

}
