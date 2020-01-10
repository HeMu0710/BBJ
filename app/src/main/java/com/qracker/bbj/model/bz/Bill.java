package com.qracker.bbj.model.bz;

import com.qracker.bbj.model.tool.Arith;
import java.util.ArrayList;

public class Bill {
    private int amount;
    private double scale = 0;
    private double[] expends;
    private ArrayList<Member> members = new ArrayList<>();


    public Bill(double... expends) {
        /**
        * @Description: 新建均摊账单，传参为任意个数double类型，初始化members
        * @Param: [expends]
        * @return:
        * @Author: HeMu-qracker
        * @Date: 2020/1/11
        */
        this.expends = expends;
        Member.nextId = 0;
        this.amount = expends.length;
        for (double exp : expends
             ) {
            this.members.add(new Member(exp));
        }
        Member.nextId = 0;
    }

    public Bill(int amount) {
        /**
        * @Description: 无参构造器，针对不同时，累计的均摊过程，例如寝室均摊等等
        * @Param: [amount]
        * @Author: HeMu-qracker
        * @Date: 2020/1/11
        */
        this.amount = amount;
        Member.nextId = 0;
        for (int i = 0; i < amount; i++) {
            members.add(new Member(0));
        }
        Member.nextId = 0;
        expends = new double[amount];
    }

    public void addExpend(int id, double expend) {
        this.expends[id] = Arith.add(expend, expends[id]);
        members.get(id).setExpend(this.expends[id]);
    }

    public int getAmount() {
        return this.amount;
    }

    public void generateSolution(ArrayList<Member> memberArrayList) {
        /**
        * @Description: 使用递归生成解决方案，传入的memberArrayList事先被遍历减去了一个average，让负的最多的
        * 成员去和正的最少的成员相抵消，生成一笔转账，并处理数额，继续寻找下一个正的最少的，如果不足则全部转出。
        * 递归出口为所有成员的expend的绝对值都小于scala
        * @Param: [memberArrayList]
        * @return: void
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
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

    public double adjustAverage(double[] expends, int amount) {
        /**
        * @Description: 获取调整至不会出现无限循环小数后的平均数，精度即为调整次数*0.01
        * @Param: [expends, amount]
        * @return: double
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double sumUp,sumDown;
        double sum = 0;
        for (int i = 0; i < expends.length; i++) {
            sum = Arith.add(sum, expends[i]);
        }
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
        /**
        * @Description: 获取解决方案，并将所有成员的转账记录合并成一个list然后return
        * @Param: []
        * @return: java.util.ArrayList<com.qracker.bbj.model.bz.Transfer>
        * @Author: HeMu-qracker
        * @Date: 2020/1/10
        */
        double average = adjustAverage(expends, amount);
        for (Member m : members
             ) {
            m.setExpend(Arith.sub(m.getExpend(), average));
        }
        this.generateSolution(this.members);
        ArrayList<Transfer> solution = new ArrayList<>();
        for (Member m : members
             ) {
            solution.addAll(m.getTransfers());
        }
        for (Member m : members
             ) {
            m.setExpend(expends[m.id]);
        }
        return solution;
    }

    public double getScale() {
        return this.scale;
    }
}
