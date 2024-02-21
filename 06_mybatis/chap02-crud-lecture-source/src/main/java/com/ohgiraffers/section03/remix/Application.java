package com.ohgiraffers.section03.remix;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 설명. remix의 핵심은 DAO 계층을 인터페이스로 만들고 추상메소드만 남기는 방식이다. */
/* 설명. 쿼리가 있는 부분은 XML로 작성한다.(mapper용 쿼리) */
public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MenuController menuController = new MenuController();

        do {
            System.out.println("========= 메뉴 관리 =========");
            System.out.println("1. 메뉴 전체 조회");
            System.out.println("2. 메뉴코드로 메뉴 조회");
            System.out.println("3. 신규 메뉴 추가");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.print("메뉴 관리 번호를 입력하세요: ");

            int no = sc.nextInt();

            switch (no){
                case 1:
                    menuController.findAllMenu();
                    break;
                case 2:
                    menuController.selectMenu(inputMenuCode());
                    break;
                case 3:
                    menuController.insertMenu(inputMenu());
                    break;
                case 4:
                    menuController.updateMenu(inputModifyMenu());
                    break;
                case 5:
                    menuController.deleteMenu(inputMenuCode());
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴를 입력하셨습니다.");
            }

        }while (true);
    }


    /* 설명. 사용자의 입력 값을 Map 형태로 반환(web에서는 key와 value형태로 request 객체에 담기는 parameter로 생각) */

    private static Map<String, String> inputMenuCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("메뉴 코드를 입력하세요: ");
        String menuCode = scanner.nextLine();

        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("menuCode", menuCode);

        return parameter;
    }
    private static Map<String, String> inputMenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("신규 메뉴의 이름을 입력 해주세요 : ");
        String menuName = scanner.nextLine();
        System.out.print("신규 메뉴 가격을 입력 해주세요: ");
        String menuPrice = scanner.nextLine();
        System.out.print("신규 카테고리 코드를 입력 해주세요: ");
        String categoryCode = scanner.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuName", menuName);
        parameter.put("menuPrice", menuPrice);
        parameter.put("categoryCode", categoryCode);

        return parameter;
    }

    private static Map<String, String> inputModifyMenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 메뉴의 번호을 입력 해주세요 : ");
        String menuCode = scanner.nextLine();
        System.out.print("변경할 메뉴의 이름을 입력 해주세요: ");
        String menuName = scanner.nextLine();
        System.out.print("신규 메뉴의 가격을 입력 해주세요: ");
        String menuPrice = scanner.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuCode", menuCode);
        parameter.put("menuName", menuName);
        parameter.put("menuPrice", menuPrice);

        return parameter;
    }
}
