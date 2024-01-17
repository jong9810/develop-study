package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }
    
    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {

    /*
        // * ObjectFactory, ObjectProvider 클래스(ObjectProvider는 ObjectFactory의 하위 인터페이스)
        // ObjectFactory나 ObjectProvider 클래스를 사용하면 getObject()를 요청할 때마다 프로토타입 빈을 새로 찾아서 반환해준다(Dependency Lookup, DL).
        // 스프링이 제공하는 기능을 사용하지만(스프링에 의존적), 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 ApplicationContext를 사용하는 것보다 훨씬 쉽다.
        // 주의!! ObjectProvider는 프로토타입 빈을 찾는 데에만 사용되는 것이 아니라, ApplicationContext 대신 빈을 찾아주는 역할에 초점이 맞춰져 있다.
        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }
    */

        // * JSR-330 Provider(자바 표준)
        // 특징
        // 1) get() 메서드 하나로 기능이 매우 단순하다.
        // 2) 별도의 라이브러리가 필요하다(build.gradle에 추가해야 함).
        // 3) 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능하다.
        // 코드를 스프링이 아닌 다른 컨테이너에서도 사용해야 한다면 JSR-330 Provider를 사용해야 한다.
        // 특별히 다른 컨테이너를 사용할 일이 없다면, 스프링이 제공하는 기능(ObjectProvider 등)을 사용하면 된다.
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    // * 프로토타입 빈
    // 프로토타입 빈은 매번 사용할 때마다 의존관계 주입이 완료된 새로운 객체가 필요할 때 사용하면 된다.
    // 실무에서는 싱글톤 빈으로 대부분의 문제를 해결할 수 있기 때문에 프로토타입 빈을 직접적으로 사용하는 경우는 매우 드물다(거의 안씀).
    // ObjectProvider, JSR-330 Provider 등은 프로토타입 뿐만 아니라 DL이 필요한 경우에는 언제든지 사용할 수 있다.
    @Getter
    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
