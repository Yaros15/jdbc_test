package org.example;

import org.example.ui.MainWindow;

public class Test {
    /*ArrayList <Customer> pup = new ArrayList<>();
    public void tut (Customer customer){
        pup.add(customer);
    }*/

    public static void main(String[] args) {

        MainWindow window = new MainWindow();
        window.show();

        /*for (int n = 0; n<2; n++) {
            Test test = new Test();
            for (int i = 0; i < 3; i++) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите имя ");
                String ima = scanner.nextLine();
                System.out.println("Введите возраст ");
                int vozr = scanner.nextInt();

                Customer cus = new Customer(ima, vozr);
                test.tut(cus);
                System.out.println("new customer: " + cus);
            }
            System.out.println(test.pup);
        }*/

    }

}
