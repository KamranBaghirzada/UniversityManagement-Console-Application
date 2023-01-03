package util;

import java.util.Scanner;

public class InputUtility {

    public static String askString(String title){
        System.out.println(title);
        return new Scanner(System.in).nextLine();
    }

    public static Integer askInt(String title){
        System.out.println(title);
        return new Scanner(System.in).nextInt();
    }
    public static Double askDouble(String title){
        System.out.println(title);
        return new Scanner(System.in).nextDouble();
    }
}
