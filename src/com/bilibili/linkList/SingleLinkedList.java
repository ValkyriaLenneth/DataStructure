package com.bilibili.linkList;

import java.util.Stack;

public class SingleLinkedList {

    public static void main(String[] args) {
        // 测试
//        HeroNode hero1 = new HeroNode(1, "Songjiang", "JiShiYu");
//        HeroNode hero2 = new HeroNode(2,"LuJunYi", "YuQiLin");
//        HeroNode hero3 = new HeroNode(3, "WuYong", "ZhiDuoXing");
//        HeroNode hero4 = new HeroNode(4, "GongSunSheng", "RuYunLong");

//        SingleLinkList singleLinkedList = new SingleLinkList();
        SingleLinkList l_1 = new SingleLinkList();
        SingleLinkList l_2 = new SingleLinkList();

        HeroNode n1 = new HeroNode(1," "," ");
        HeroNode n1_1 = new HeroNode(1," ", " ");
        HeroNode n1_2 = new HeroNode(1," ", " ");
        HeroNode n2 = new HeroNode(2," "," ");
        HeroNode n3 = new HeroNode(3," "," ");
        HeroNode n4 = new HeroNode(4," "," ");
        HeroNode n5 = new HeroNode(5," "," ");
        HeroNode n5_1 = new HeroNode(5," "," ");
        HeroNode n5_2 = new HeroNode(5," "," ");
        HeroNode n6 = new HeroNode(6," "," ");
        HeroNode n7 = new HeroNode(7," "," ");
        HeroNode n8 = new HeroNode(8," "," ");

        l_1.add(n1);
        l_1.add(n1_1);
        l_1.add(n3);
        l_1.add(n5);
        l_1.add(n8);

        l_2.add(n2);
        l_2.add(n4);
        l_2.add(n5_2);
        l_2.add(n5_1);
        l_2.add(n6);
        l_2.add(n7);


        SingleLinkList l_3 = new SingleLinkList();
        l_3 = mergeList(l_1.getHead(), l_2.getHead());
        System.out.println("   ");
        l_3.list();

//        singleLinkedList.addOrder(hero4);
//        singleLinkedList.addOrder(hero1);
//        singleLinkedList.addOrder(hero3);
//        singleLinkedList.addOrder(hero2);
////        singleLinkedList.addOrder(hero1);

//        HeroNode newheronode = new HeroNode(2, "卢俊义", "玉麒麟");
//        singleLinkedList.update(newheronode);
//        singleLinkedList.delete(4);
//        singleLinkedList.list();
//        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
//        System.out.println("res=" + res);
//
//        SingleLinkList reversed = reverse(singleLinkedList.getHead());
//        reversed.list();
//        reversePrint(reversed.getHead());
    }

    //方法: 返回有效长度， 不统计头结点
    public static int getLength(HeroNode head) {
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找倒数第K个节点：
//1、 先接受head节点，同时接受一个index，表示倒数第index个
//2. 先遍历链表获取有效长度
//3. 然后获取第 length-index个节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        // 判断是否非空
        if (head.next == null ){
            return null;
        }
        // 首先获取长度
        int length = getLength(head);
        // 遍历到 length - index = k
        // 首先校验 index
        if (index <=0 || index > length) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i=0; i< length-index; i++){
            cur = cur.next;
        }
        return cur;
    }

    // 翻转链表：
    // 1.创建一个新的链表
    // 2.从头遍历，每遍历到一个节点，就把这个节点扔到新的链表
    public static SingleLinkList reverse(HeroNode head) {
        if (head == null || head.next == null){
            return null;
        }
        SingleLinkList reversed = new SingleLinkList();
        HeroNode cur = head;
        while (cur.next != null) {
            HeroNode temp = cur.next;
            cur.next = temp.next;
            reversed.addHead(temp);
        }
        return reversed;
    }

    // 逆序打印
    // 利用stack来逆序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null){
            return;
        }
        // Create a stack
        Stack<HeroNode> stack =  new Stack<HeroNode>();
        HeroNode cur = head;
        while (cur.next != null) {
            stack.push(cur.next);
            cur = cur.next;
        }
        // Print
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序列表
    public static SingleLinkList mergeList(HeroNode head1, HeroNode head2){
        if (head1.next == null || head2.next == null) {
            return null;
        }
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        SingleLinkList mergedList = new SingleLinkList();
        HeroNode cur3 = mergedList.getHead();

//        HeroNode temp = new HeroNode(0, "", "");
        while (cur1 != null && cur2 != null){
            if (cur1.no >= cur2.no) {
                cur3.next = cur2;
                cur2 = cur2.next;
                cur3 = cur3.next;
                cur3.next = null;
            }else {
                cur3.next = cur1;
                cur1 = cur1.next;
                cur3 = cur3.next;
                cur3.next = null;
            }
        }
        // 插入剩下的
        if (cur1 != null){
            while (cur1 == null) {
                cur3.next = cur1;
                cur1 = cur1.next;
                cur3 = cur3.next;
                cur3.next = null;
            }
        }
        if (head2.next != null) {
            while (head2.next == null) {
                cur3.next = cur2;
                cur2 = cur2.next;
                cur3 = cur3.next;
                cur3.next = null;;
            }
        }
        return mergedList;
    }
}

// 定义Single Linked List 来管理英雄录
class SingleLinkList {
    // 初始化Head节点
    private HeroNode head = new HeroNode(0, "", "");

    //添加到最后
    public void add(HeroNode heroNode) {

        // 找到最后的节点
        HeroNode temp = head;
        while (temp != null) {
            if (temp.next == null){
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    // 头插
    public void addHead(HeroNode heroNode){
        heroNode.next = head.next;
        head.next = heroNode;
    }

    // 按排名顺序添加
    public void addOrder(HeroNode heroNode) {
        // 插入位置的前一个节点，因为单链表没法回头
        HeroNode temp = head;
        boolean flag = false; //添加的编号是否为存在

        while (true) {
            if (temp.next == null){
                //说明temp在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                // 位置找到了， 因为所有节点都是按顺序添加的，因此只要找到第一个比heroNode大的节点就行
                break;
            }else if (temp.next.no == heroNode.no) {
                //此时说明编号存在
               flag = true;
                // 当编号相同时直接插
                break;
            }
            temp = temp.next;
        }
        if (flag) {
             System.out.println("This no is existed");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //根据no修改信息
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("List is empty");
            return;
        }
        //找到需要修改的节点
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break; // 表示遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("No:%d.Node does not exist", newHeroNode.no);
        }
    }

    //删除
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("List is empty");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break; //说明没找到这个节点
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true){
            temp.next = temp.next.next;
        } else {
            System.out.printf("There is no NO:%d.node in the list", no);
        }

    }
    // 遍历
    public void list() {
         // 判断是否为空
        if (head.next == null) {
            System.out.println("List is empty!!");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public HeroNode getHead() {
        return head;
    }

}

//定义node
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

     //显示方便重写toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + "}"
                ;
    }
}


