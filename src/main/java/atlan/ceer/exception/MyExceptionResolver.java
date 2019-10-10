package atlan.ceer.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        //获取到异常对象
        MyException e=null;
        if (ex instanceof MyException){
            e= (MyException) ex;
        }else {
            e=new MyException("系统正在维护。");
        }
        //创建ModelAndView对象
        ModelAndView mv=new ModelAndView();
        mv.addObject("errorMsg",e.getMessages());
        mv.setViewName("error");
        return mv;
    }
}
