package atlan.ceer.contoller;

import atlan.ceer.exception.MyException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/getMsg")
    public void test1(int type){
        System.out.println(type);
    }

    /**
     * 传统文件上传
     * @param request
     */
    @RequestMapping("/upload")
    public void upload(HttpServletRequest request) {
        try {
            System.out.println("文件上传。。");
            //使用fileupload组件完成上传
            //上传位置
            //String path=request.getSession().getServletContext().getRealPath("/uploads/");
            String path="d://upload/";
            File file=new File(path);
            if (!file.exists()){
                //没有就创建该文件夹
                System.out.println("不存在");
                file.mkdirs();
            }
            //解析request对象。获取上传文件项
            DiskFileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            //解析request
            List<FileItem> fileItems = upload.parseRequest(request);
            //遍历
            for (FileItem item:fileItems){
                //进行判断，当前item对象是否是上传文件项
                if (item.isFormField()){
                    //说明普通表单项
                    System.out.println("普通表单");
                }else {
                    //说明上传文件项
                    //获取上传文件的名称
                    String filename=item.getName();
                    //吧文件的名称设置唯一值，uuid
                    String uuid= UUID.randomUUID().toString().replace("-","");
                    //完成文件上传
                    filename=uuid+"_"+filename;
                    item.write(new File(path,filename));
                    //删除临时文件
                    item.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 跨服务器文件上传
     * @return
     */
    @RequestMapping("/uploadToOther")
    public String uploadToOther(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传...");

        // 定义上传文件服务器路径
        String path = "http://localhost:9090/uploads/";

        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;

        // 创建客户端的对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        // 上传文件
        webResource.put(upload.getBytes());

        return "success";
    }

    /**
     * SpringMVC文件上传
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadMVC")
    public String uploadMVC(MultipartFile upload) throws Exception {
        System.out.println("springmvc文件上传...");

        // 使用fileupload组件完成文件上传
        // 上传的位置
        //String path = request.getSession().getServletContext().getRealPath("/uploads/");
        String path="/image";

        // 判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            // 创建该文件夹
            file.mkdirs();
        }

        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        // 完成文件上传
        upload.transferTo(new File(path,filename));

        return "success"+path+filename;
    }

    @RequestMapping("/exception")
    public String testException() throws MyException {
        System.out.println("testException执行了...");
        try {
            int a=20/10;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("something wrong!");
        }
        return "success";
    }
    @RequestMapping("/interceptor")
    public String testInterceptor(){
        System.out.println("testInterceptor");
        return "success";
    }
}
