package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class PrototypeTest {

    @Test
    public void clientTest() {

    }

    @Test
    public void prototypeTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);


        int logic1 = clientBean1.logic();
        int logic2 = clientBean2.logic();
        System.out.println("logic1 = " + logic1);
        System.out.println("logic2 = " + logic2);
        Assertions.assertThat(logic1).isSameAs(logic2);

    }


    @Configuration
    static class Config {

        @Bean
        public ClientBean clientBean() {
            return new ClientBean(prototypeProvider());
        }

        @Bean
        public Provider<PrototypeBean> prototypeProvider() {
            return new PrototypeProvider();
        }

        @Bean("prototype")
        public PrototypeBean prototypeBean() {
            return new PrototypeBean();
        }
    }

    static class PrototypeProvider implements Provider<PrototypeBean> {

        @Override
        public PrototypeBean get() {
            return new PrototypeBean();
        }
    }



    static class ClientBean {
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public ClientBean(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }


    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.close");
        }

        public void addCount() {
            count++;
        }
    }
}
