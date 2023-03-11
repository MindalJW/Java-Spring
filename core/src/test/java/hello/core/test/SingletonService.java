package hello.core.test;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();//자기자신 안에 자기자신을 참조하여 인스턴스를 하나만들어둔다

    public static  SingletonService getInstance() {
        return instance;//그리고 외부에서 가져다 쓰려고할때 미리만들어둔 인스턴스를 리턴한다 -> 싱글톤 패턴
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤객체호출");
    }

}
