package $03_io.polymorphism;

public class Parent {
    int a = 1;

    void b(){
        System.out.println("Parent constructor, a = "+a);
    }

    void c(){
        System.out.println("Parent에만 있는 method c 입니다");
    }
}
