package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

// @HandlesTypes : 인터페이스를 지정하면 구현체를 찾아서 클래스 정보를 매개변수로 넘겨준다.
@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 ctx = " + ctx);

        // class hello.container.AppInitV1Servlet
        for (Class<?> appInitClass : c) {
            try {
                // new AppInitV1Servlet()와 같은 코드
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// WAS 실행 -> 서블릿 컨테이너 초기화 -> 애플리케이션 초기화
//             MyContainerInitV2      AppInitV1Servlet(AppInit의 구현체)
