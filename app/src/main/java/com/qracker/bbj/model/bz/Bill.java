package com.qracker.bbj.model.bz;

import com.qracker.bbj.model.tool.Arith;
import java.util.ArrayList;

public class Bill {
    private String comment;
    private int amount = 0;
    private double scale = 0;
    private double[] expends;
    private ArrayList<Member> members = new ArrayList<>();
    private boolean isFinished;


    public Bill(String comment) {
        /**
        * @Description: 新建均摊账单
        * @Param: [comment,expends]
        * @return:
        * @Author: HeMu-qracker
        * @Date: 2020/1/11
        */
        this.comment = comment;
        this.isFinished = false;
        expends = new double[20];
    }

    public void addMember(String name, double expend) {
        Member member = new Member(name, expend);
        member.setId(amount);
        members.add(member);
        amount ++;
        expends[member.getId()] = expend;
    }

    public void addExpend(String name, double expendToAdd) {
        Member temp;
        for (int i = 0; i < members.size(); i++) {
            temp = members.get(i);
            if(temp.getName().equals(name)) {
                expends[i] = Arith.add(temp.getExpend(), expendToAdd);
                temp.setExpend(expends[i]);
            }
        }
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
        Member min = new Member(" ");
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
            Member minPositive = new Member(" ");
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
        if(!isFinished) {
            double average = adjustAverage(expends, amount);
            for (Member m : members
            ) {
                m.setExpend(Arith.sub(m.getExpend(), average));
            }
            this.generateSolution(this.members);
            this.isFinished = true;
        }
        ArrayList<Transfer> solution = new ArrayList<>();
        for (Member m : members
             ) {
            solution.addAll(m.getTransfers());
        }
        for (Member m : members
             ) {
            m.setExpend(expends[m.getId()]);
        }
        return solution;
    }

    public double getScale() {
        return this.scale;
    }

    public String getComment() {
        return comment;
    }

    public ArrayList<Member> getMembers() {
        return this.members;
    }

    public void setComment(String newComment) {
        this.comment = newComment;
    }

    public boolean isFinished() {
        return isFinished;
    }
}
