package machine;
import java.util.Scanner;

public class CoffeeMachine {
    enum States {
            CHOSING_AN_ACTION, BUY, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUPS
    }

    States machineState = States.CHOSING_AN_ACTION;
    int water = 400;
    int milk = 540;
    int coffeeBeans = 120;
    int disposableCups = 9;
    int money = 550;
    boolean state = true;

    public CoffeeMachine(){
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
    }

    void state(){
        System.out.println("\nThe coffee machine has:");
        System.out.println(this.water+" of water");
        System.out.println(this.milk+" of milk");
        System.out.println(this.coffeeBeans+" of coffee beans");
        System.out.println(this.disposableCups+" of disposable cups");
        System.out.println(this.money+" of money");
    }

    boolean check_resources(int n){
        switch (n){
            case 1:
                if (this.water>=250 && this.coffeeBeans>=16 && this.disposableCups>=1){
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (this.water>=350 && this.milk>=75 && this.coffeeBeans>=20 && this.disposableCups>=1){
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (this.water>=200 && this.milk>=100 && this.coffeeBeans>=12 && this.disposableCups>=1){
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }

    void action(String str){
        switch (this.machineState) {
            case CHOSING_AN_ACTION:
                switch (str) {
                    case "buy":
                        this.machineState = States.BUY;
                        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        break;
                    case "fill":
                        this.machineState = States.FILL_WATER;
                        System.out.println("\nWrite how many ml of water do you want to add:");
                        break;
                    case "take":
                        System.out.println("I gave you $" + this.money + "\n");
                        this.money = 0;
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    case "remaining":
                        this.state();
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    case "exit":
                        this.state = false;
                        break;
                    default:
                        System.out.println("Not a valid option !");
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                }
                break;
            case BUY:
                switch (str) {
                    case "1":
                        if (this.check_resources(1)) {
                            this.water -= 250;
                            this.coffeeBeans -= 16;
                            this.money += 4;
                            this.disposableCups--;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("I don't have enough resources and can't make you a coffee!");
                        }
                        this.machineState = States.CHOSING_AN_ACTION;
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    case "2":
                        if (this.check_resources(2)) {
                            this.water -= 350;
                            this.milk -= 75;
                            this.coffeeBeans -= 20;
                            this.money += 7;
                            this.disposableCups--;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("I don't have enough resources and can't make you a coffee!");
                        }
                        this.machineState = States.CHOSING_AN_ACTION;
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    case "3":
                        if (this.check_resources(3)) {
                            this.water -= 200;
                            this.milk -= 100;
                            this.coffeeBeans -= 12;
                            this.money += 6;
                            this.disposableCups--;
                            System.out.println("I have enough resources, making you a coffee!");
                        } else {
                            System.out.println("I don't have enough resources and can't make you a coffee!");
                        }
                        this.machineState = States.CHOSING_AN_ACTION;
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    case "back":
                        this.machineState = States.CHOSING_AN_ACTION;
                        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                        break;
                    default:
                        System.out.println("Not a valid option!\n");
                        System.out.println("Write action (buy, fill, take, remaining, exit):");
                }
                break;
            case FILL_WATER:
                this.water += Integer.valueOf(str);
                System.out.println("Write how many ml of milk do you want to add:");
                this.machineState = States.FILL_MILK;
                break;
            case FILL_MILK:
                this.milk += Integer.valueOf(str);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                this.machineState = States.FILL_COFFEE;
                break;
            case FILL_COFFEE:
                this.coffeeBeans += Integer.valueOf(str);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                this.machineState = States.FILL_CUPS;
                break;
            case FILL_CUPS:
                this.disposableCups += Integer.valueOf(str);
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                this.machineState = States.CHOSING_AN_ACTION;
                break;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CoffeeMachine myMachine = new CoffeeMachine();

        while (myMachine.state){
            String str = input.next();
            myMachine.action(str);
        }
    }
}
