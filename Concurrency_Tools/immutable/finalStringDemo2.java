package immutable;


import javax.swing.*;

public class finalStringDemo2 {
    public static void main(String[] args) {
        String a="aaa1";
        final String b=getName();
        String c=b+1;
        System.out.println((a==c));

        Thread.currentThread().getName()
    }

    private static String getName(){
        return "aaa";
    }
}
