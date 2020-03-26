package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.input.MouseEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Controller {
    //ModPower
    public TextField base;
    public TextField exponent;
    public TextField mod;
    public Button compute;
    public Label equation;
    public Label result;
    public Button calculateMortgage;
    private int b, e, m, answer;

    public void calculate(ActionEvent actionEvent) {
        base.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Error.errorBaseLetter();
                base.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        exponent.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Error.errorExpLetter();
                exponent.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        mod.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Error.errorModLetter();
                mod.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        if (base.getText().equals("") || exponent.getText().equals("") || mod.getText().equals("")) {
            Error.errorNoValue();
        }
        b = Integer.parseInt(base.getText());
        e = Integer.parseInt(exponent.getText());
        m = Integer.parseInt(mod.getText());
        if (b < 1) Error.errorBaseLetter();
        else if (e < 0) Error.errorExpLetter();
        else if (m < 0) Error.errorModLetter();
        answer = modPower(b, e, m);
        //equation.setText(b + "^" + e + " mod " + m);
        result.setText(Integer.toString(answer));
        //equation.setText(Double.toString((Math.pow(7, 251))%11));
        equation.setText("Answer: ");
    }

    @Test
    public static int modPower(int base, float exponent, int mod) {
        int power = 1, exp = (int) exponent, result = 1, temp = 0, one = 1, two = 0, size = 0;
        ArrayList<Integer> binary = new ArrayList<>(50);
        do {
            if ((exponent % 2) == 1) {
                exponent = exponent - 1;
                exponent = exponent / 2;
                binary.add(one);
            } else {
                exponent = exponent / 2;
                binary.add(two);
            }
        } while (exponent >= 1);
        //assertEquals(binary.get(0), 1);
        System.out.println(binary.size());
        System.out.println(binary);
        System.out.println((Math.pow(3, 64)) % 25);
        //Collections.reverse(binary);
        binary.trimToSize();
        size = binary.size();
        for (int i = 0; i < size; i++) {
            if (binary.get(0) != 0) {
                System.out.println(binary);
                System.out.println("yes");
                exp = exp - power;
                System.out.println("exp: " + exp + "power : " + power);
                temp = (int) Math.pow(base, power);
                result = ((temp % mod) * result) % mod;
                System.out.println("temp mod" + temp % mod);
                System.out.println("temp: " + temp);
                power *= 2;
                binary.remove(0);
            } else if (binary.get(0) == 0) {
                System.out.println(binary);
                power *= 2;
                binary.remove(0);
            }
        }
        return result;
    }

    //Mortgage Calculator
    public TextField principleField;
    public TextField interestField;
    public ChoiceBox payFreqBox;
    public Slider amortTime;
    public Label numberOfPayments;
    public Label mortgagePayment;
    public Label principlePayments;
    public Label interestPayments;
    public Label totalCost;
    int principle = 0;
    int frequency = 0;
    int n = 0;
    int a = 0;
    double interest = 0.0;
    double ans = 0.0;
    String temp = "";

    DecimalFormat one = new DecimalFormat("0.00");

    public void calculateMortgage(ActionEvent actionEvent) {
        /*principleField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Error.errorBaseLetter();
                principleField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });*/
        interestField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                Error.errorBaseLetter();
                interestField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        if (payFreqBox.isShowing()) {
            Error.errorChoice();
        }
        principle = Integer.parseInt(principleField.getText());
        if (payFreqBox.getValue().equals("Weekly")) frequency = 52;
        else if (payFreqBox.getValue().equals("Bi-Weekly")) frequency = 26;
        else if (payFreqBox.getValue().equals("Semi-Monthly")) frequency = 24;
        else if (payFreqBox.getValue().equals("Monthly")) frequency = 12;
        a = (int) amortTime.getValue();
        interest = Double.parseDouble(interestField.getText());
        interest /= 100;
        interest = interest / 365 * 30; //We are using daily interest rate
        n = a * frequency;
        ans = Mortgage.mortgageTotal(principle, interest, n);
        totalCost.setText(String.valueOf(one.format(ans)));
        principlePayments.setText(String.valueOf(principle));
        interestPayments.setText(String.valueOf(one.format(ans - principle)));
        mortgagePayment.setText(String.valueOf(one.format(Mortgage.mortgagePayment(principle, interest, n))));
        numberOfPayments.setText(String.valueOf(frequency * a));
    }

    public void showFreqOptions(MouseEvent mouseEvent) {
        ObservableList<String> freq = FXCollections.observableArrayList("Weekly", "Bi-Weekly", "Semi-Monthly", "Monthly");
        payFreqBox.setItems(freq);
    }

    @Test
    public void testModPower() {
        assertEquals(modPower(7, 251, 11), 7);
        assertEquals(modPower(3, 2, 5), 4);
        assertEquals(modPower(6, 1241, 7), 6);
        assertEquals(modPower(2, 2, 3), 1);
    }
}
