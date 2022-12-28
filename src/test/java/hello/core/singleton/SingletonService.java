package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // 자기 자신을 내부에 private로 static 으로 선언되었기 때문에 클래스 레벨에 하나만 올라가게 된다.

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
