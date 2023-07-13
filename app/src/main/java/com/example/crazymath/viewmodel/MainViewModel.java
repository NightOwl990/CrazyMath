package com.example.crazymath.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainViewModel extends BaseViewModel {
    private final MutableLiveData<String> expression = new MutableLiveData<>("");
    private final MutableLiveData<Integer> ansA = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> ansB = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> ansC = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> score = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> best = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> time = new MutableLiveData<>(10);
    private int answer;
    private Thread thread;

    public void initExpression() {
        Random rd = new Random();
        int a = rd.nextInt(9) + 1;
        int b = rd.nextInt(9) + 1;
        int sign = rd.nextInt(10);
        expression.postValue(String.format("%s%s%s=?", a, sign > 5 ? "+" : "-", b));

        answer = sign > 5 ? a + b : a - b;

        int wrong1 = answer + (rd.nextInt(2) + 1);
        int wrong2 = answer - (rd.nextInt(2) + 1);

        List<Integer> listInt = new ArrayList<>(Arrays.asList(answer, wrong1, wrong2));
        Collections.shuffle(listInt);
        ansA.postValue(listInt.get(0));
        ansB.postValue(listInt.get(1));
        ansC.postValue(listInt.get(2));
        time.setValue(10);
    }

    public boolean checkAnswer(String textAns) {
        int intAns = Integer.parseInt(textAns);
        if (intAns == answer) {
            if (score.getValue() == null) return false;
            score.postValue(score.getValue() + 1);
            initExpression();
            return true;
        }
        return false;
    }

    public void startCowndown() {
        if (thread == null || !thread.isAlive()) {
            thread = new Thread(() -> {
                while (time.getValue() != null && time.getValue() > 0) {
                    try {
                        Thread.sleep(1000);
                        time.postValue(time.getValue() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                thread = null;
            });
            thread.start();
        }

    }

    public Thread getThread() {
        return thread;
    }

    public MutableLiveData<String> getExpression() {
        return expression;
    }

    public MutableLiveData<Integer> getAnsA() {
        return ansA;
    }

    public MutableLiveData<Integer> getAnsB() {
        return ansB;
    }

    public MutableLiveData<Integer> getAnsC() {
        return ansC;
    }

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public MutableLiveData<Integer> getScore() {
        return score;
    }

    public MutableLiveData<Integer> getBest() {
        return best;
    }


    public void gameOver() {
        thread.interrupt();
        time.postValue(0);

    }

    public void playAgain() {
        score.postValue(0);
        initExpression();
        startCowndown();
    }
}
