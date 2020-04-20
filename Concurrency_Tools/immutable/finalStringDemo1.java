package immutable;


public class finalStringDemo1 {


    public static void main(String[] args) {
        String a="kk1";
        final String b="kk";
        String d="kk";
        String c=b+1;
        String e=d+1;
        System.out.println((a==c));
        System.out.println((a==e));
    }
}
