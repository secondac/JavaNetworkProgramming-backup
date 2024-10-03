package io.polymorphism;

public class Main1 {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        System.out.print("parent.b() : ");
        parent.b();
        parent.c();
        System.out.println("==============");

        System.out.print("child.b() : ");
        child.b();
        child.c();

        System.out.println("==============");

        Parent parent2 = new Child();
        System.out.print("parent2.b() : ");
        parent2.b();
        parent2.c();

    }
}
