package com.mumu.dp3_1_cor;

// 审批者类：抽象处理者
public abstract class Approver {
    // 定义后继对象
    protected Approver successor;
    // 审批者姓名
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    // 设置后继者
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    // 抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}
