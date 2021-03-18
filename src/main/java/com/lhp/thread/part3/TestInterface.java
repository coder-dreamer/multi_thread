package com.lhp.thread.part3;

/**
 * @author 53137
 */
public interface TestInterface {

    static void test() {
        System.out.println("JDK1.8接口中定义静态方法");
    }

    default void test1() {
        System.out.println("JDK1.8接口中定义普通方法");
    }
}

class Test {
    public static void main(String[] args) {
        TestInterface.test();
        TestInterface test = new TestInterface() {};
        test.test1();

        TestInterface test1 = new TestInterface() {
            @Override
            public void test1() {
                System.out.println("重写普通方法");
            }
        };
        test1.test1();

    }
}
