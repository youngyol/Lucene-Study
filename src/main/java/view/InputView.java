package view;

import java.util.Scanner;

public class InputView {
    public static String inputCommand() {
        System.out.println("명령어를 입력하세요.");
        return new Scanner(System.in).nextLine();
    }

    public static String inputSearchTerm() {
        System.out.println("검색어를 입력하세요");
        return new Scanner(System.in).nextLine();
    }
}
