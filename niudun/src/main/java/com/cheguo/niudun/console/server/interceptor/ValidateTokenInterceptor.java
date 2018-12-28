package com.cheguo.niudun.console.server.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验用户权限
 *
 * @author spy
 * @version 1.0 2018-04-25
 * @since 1.0
 */
@Slf4j
public class ValidateTokenInterceptor implements HandlerInterceptor {

    @Value("${mock.userSession:false}")
    private boolean mockSession;

    @Value("${mock.userId:1}")
    private Long mockUserId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug(">>>>>>> pre handle");

//        HttpHeaderDto httpHeaderDto = HttpContext.get();


        if (mockSession) {
            log.info("MOCK user session, this should not be in production!");

//            httpHeaderDto.setUserId(mockUserId);

            return true;
        }

//        UserSession userSessionQuery = UserSession.builder()
//                                                  .token(httpHeaderDto.getToken())
//                                                  .deviceId(httpHeaderDto.getDeviceId())
//                                                  .channelCode(httpHeaderDto.getChannel())
//                                                  .build();
//        UserSessionValidateResult result = userSessionService.validateToken(userSessionQuery);
//        if (result.getSuccess()) {
//
//            // check user state
//            User user = userService.selectByUserId(result.getUserId());
//            if (user.getIsLock()) {
//                throw new BaseAppException(UserErrorConst.USER_LOCKED);
//            }
//
//            httpHeaderDto.setUserId(result.getUserId());
//        } else {
//            throw new BaseAppException(result.getErrorCode());
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug(">>>>>>> completion");
    }
}
