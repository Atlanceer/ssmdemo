package atlan.ceer.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class testDemo1 {
    @Test
    public void md5(){
        String password="test";
        String md5password= DigestUtils.md5DigestAsHex(password.getBytes());
        String md5password2=DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println("==========================");
        System.out.println(md5password);
        System.out.println(md5password2);
    }
    @Test
    public void testUUID(){
        String uuid= UUID.randomUUID().toString().replace("-","");
        System.out.println("this==========================");
        System.out.println(uuid);
    }

}
