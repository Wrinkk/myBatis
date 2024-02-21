package com.ohgiraffers.section01.xml;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("=========== 마이바티스 동적 SQL ==========");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose(when, otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim(where, set) 확인하기");
            System.out.println("9. 종료하기");
            System.out.print("메뉴를 선택하세요: ");
            int no = scanner.nextInt();

            switch (no) {
                case 1:
                    ifSubMenu();
                    break;
                case 2:
                    chooseSubMenu();
                    break;
                case 3:
                    foreachSubMenu();
                    break;
                case 4:
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }while (true);
    }

    public static void ifSubMenu() {
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("============= if 서브 메뉴 ============");
            System.out.println("1. 원하는 금액대에 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력해주세요: ");
            int no = scanner.nextInt();
            switch (no) {
                case 1:
                    menuService.findMenuByPrice(inputPrice());
                    break;
                case 2:
                    menuService.searchMenu(inputSearchCriteria());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    public static int inputPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("검색 할  가격대의 최대 금액을 입력해 주세요");

        return scanner.nextInt();
    }

    public static SearchCriteria inputSearchCriteria() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색 기준을 입력해 주세요(name or category): ");
        String condition = scanner.nextLine();
        System.out.print("검색어를 입력해주세요: ");
        String value = scanner.nextLine();

        return new SearchCriteria(condition, value);
    }

    public static void chooseSubMenu() {
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("========= choose 서브 메뉴 =========");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요: ");
            int no = scanner.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuBySupCategory(inputSupCategory());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    private static SearchCriteria inputSupCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("상위 분류를 입력 해주세요(식사, 음료, 디저트): ");
        String value = scanner.nextLine();

        return new SearchCriteria("category", value);
    }

    public static void foreachSubMenu() {
        Scanner scanner = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("=========== foreach 서브 메뉴 =============");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요: ");
            int no = scanner.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuByRandomMenuCode(generateRandomMenuCodeList());
                    break;
                case 9:
                    return;
            }
        } while (true);
    }

    /* 설명. 1부터 21까지의 5개의 중복되지 않는 번호 생성해 List에 쌓아 반환하는 메소드 */
    private static List<Integer> generateRandomMenuCodeList() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) {
            int temp = (int) (Math.random() * 21) + 1;
            set.add(temp);
        }

        /* 설명. set을 List형태로 바꿔서 오름차순 정렬까지 진행 */
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        System.out.println("랜덤수 생성한 메소드가 반환하는 list: " + list);
        return list;
    }
}
