import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo01{


    public static void main(String[] args) {

        MyInter1 inter = ()->System.out.println("Lambda");
        inter.test1();
    }
}

interface MyInter1{
    void test1();
    //void test2();
}
