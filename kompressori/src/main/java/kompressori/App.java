package kompressori;

import java.util.Scanner;
import kompressori.ui.UI;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        UI ui = new UI(new Scanner(System.in));
        ui.start();
    }
}
