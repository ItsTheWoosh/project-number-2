package sample;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Mortgage {
    private int principle;
    private int paymentFrequency;
    private int term;
    private int amort;
    private int n;
    private double interest;

    Mortgage(int principle, int paymentFrequency, int term, int amort, int n, double interest) {
        this.principle = principle;
        this.paymentFrequency = paymentFrequency;
        this.term = term;
        this.amort = amort;
        this.n = n;
        this.interest = interest;
    }

    public static double mortgageTotal (int p, double i, int n) {
        double ans = 0;
        ans = mortgagePayment(p, i, n);
        ans *= n;
        ans = round (ans, 3);
        return ans;
    }

    public static double mortgagePayment (int p, double i, int n) {
        double ans = 0.0;
        ans = Math.pow((1 + i), n);
        ans = round(ans, 3);
        ans = (ans - 1) / (ans * i);
        ans = round(ans, 3);
        ans = p / ans;
        return ans;
    }

    public static double round(double num, int digits) {
        digits *= 10;
        num = Math.round(num * digits);
        num /=  digits;
        return num;
    }

    @Test
    public void testMortgage() {
        assertEquals(mortgagePayment(500000, 0.05 / 365 * 30, 300), 2899.11);
        assertEquals(mortgagePayment(100000, 0.3 / 365 * 30, 1040), 2465.08);
        assertEquals(mortgageTotal(500000, 0.05 / 365 * 30, 300), 869733.27);
        assertEquals(mortgageTotal(100000, 0.3 / 365 * 30, 1040), 2563681.17);
    }

}
