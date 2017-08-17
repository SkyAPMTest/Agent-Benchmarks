package org.skywalking.apm.benchmark.example.util;

public class Executor {

    private static Executor INSTANCE = new Executor();

    public void doExecute() {
        for (int i = 0; i < 533; i++) {
            double sum = 0;
            sum += i;
            double tmp = sum / (i + 1);
            tmp += sum;
            sum += tmp;
            sum = Math.log10(sum) * Math.log1p(sum) / Math.asin(sum) + Math.cos(sum);
        }

        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Executor Instance() {
        return INSTANCE;
    }
}
