package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 만원을 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 2만원을 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자A가 주문 금액을 조회
        //int price = statefulService1.getPrice();
        //System.out.println("price = " + price);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 만원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 2만원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자A가 주문 금액을 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    @Configuration
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
