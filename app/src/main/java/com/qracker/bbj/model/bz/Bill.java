package com.qracker.bbj.model.bz;

import com.qracker.bbj.tool.Arith;
import java.util.ArrayList;

public class Bill {
    private int amount;
    private double scale = 0;
    private ArrayList<Member> members = new ArrayList<>();

    public Bill(double... expends) {
        Member.nextId = 0;
        this.amount = expends.length;
        double sum = 0;
        for (int i = 0; i < expends.length; i++) {
            sum += expends[i];
        }
        double average = adjustAverage(sum, amount);
        for (int i = 0; i < expends.length; i++) {
            expends[i] = Arith.sub(expends[i], average);
        }
        for (double exp : expends
             ) {
            this.members.add(new Member(exp));
        }
        Member.nextId = 0;
    }

    public int getAmount() {
        return this.amount;
    }

    public void generateSolution(ArrayList<Member> memberArrayList) {
        boolean isFinish = true;
        for (Member m : memberArrayList //跳出递归条件
             ) {
            if(Math.abs(m.getExpend()) > scale){
                isFinish = false;
            }
        }
        if(isFinish)
            return;
        Member min = new Member(0);
        for (Member m : memberArrayList
             ) {
            if(Math.abs(m.getExpend()) <= scale && Math.abs(m.getExpend()) == 0)
                continue;
            else {
                min = m;
                break;
            }
        }
        for (Member m : memberArrayList
             ) {
            if(m.getExpend() < min.getExpend())
                min = m;
        }

        while(Math.abs(min.getExpend()) >= scale && Math.abs(min.getExpend()) > 0){
            int notZero = 0;
            for (Member m : memberArrayList
                 ) {
                if(Math.abs(m.getExpend()) >= 0.001)
                    notZero ++;
            }
            if(notZero == 1){
                break;
            }
            Member minPositive = new Member(0);
            for (Member m : memberArrayList
            ) {
                if(m.getExpend() < scale || m.getExpend() == 0)
                    continue;
                else {
                    minPositive = m;
                    break;
                }
            }
            for (Member m : memberArrayList
            ) {
                if(m.getExpend() > scale && m.getExpend() < minPositive.getExpend())
                    minPositive = m;
            }
            if(min.getExpend() + minPositive.getExpend() < 0){
                min.addTransfer(minPositive.getExpend(),minPositive);
            }
            else{
                min.addTransfer(min.getExpend() * (-1),minPositive);
            }
        }

        generateSolution(memberArrayList);

    }
    public double adjustAverage(double sum, int amount) {
        double sumUp,sumDown;
        if((Arith.mul(sum, 100)) % amount == 0)
            return Arith.div(sum, amount, 2);
        else{
            while(true) {
                sumUp = Arith.round(Arith.add(sum, 0.01), 2);
                sumDown = Arith.round(Arith.sub(sum, 0.01) ,2);
                scale = Arith.add(scale, 0.01);
                if((int)Arith.mul(sumUp, 100) % amount == 0)
                    return Arith.div(sumUp, amount, 2);
                if((int)Arith.mul(sumDown, 100) % amount == 0)
                    return Arith.div(sumDown, amount, 2);
            }
        }
    }

    public ArrayList<Transfer> getSolution() {
        this.generateSolution(this.members);
        ArrayList<Transfer> solution = new ArrayList<>();
        for (Member m : members
             ) {
            solution.addAll(m.getTransfers());
        }
        return solution;
    }

    public double getScale() {
        return this.scale;
    }
}
