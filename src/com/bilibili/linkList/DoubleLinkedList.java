package com.bilibili.linkList;

public class DoubleLinkedList {
    public static void main(String[] args) {
        // Test
        System.out.println("Test DouleLinkedList");
        // Create Node:
        HeroNode2 hero1 = new HeroNode2(1, "Songjiang", "JiShiYu");
        HeroNode2 hero2 = new HeroNode2(2,"LuJunYi", "YuQiLin");
        HeroNode2 hero3 = new HeroNode2(3, "WuYong", "ZhiDuoXing");
        HeroNode2 hero4 = new HeroNode2(4, "GongSunSheng", "RuYunLong");
        // Create DouleLinkList
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(hero1);
        doubleLinkList.add(hero2);
        doubleLinkList.add(hero3);
//        doubleLinkList.add(hero4);

        doubleLinkList.list();

        // Update
//        HeroNode2 newNode = new HeroNode2(4, "x", "x");
//        doubleLinkList.update(newNode);

        // Delete
//        doubleLinkList.delete(3);
        doubleLinkList.addNo(hero4);
        doubleLinkList.list();
    }
}

// 创建双向链表
class DoubleLinkList{

    // Init Head
    private HeroNode2 head = new HeroNode2(0, "", "");

    // Get Head Node
    public HeroNode2 getHead() {
        return head;
    }

    // traverse DoubleLinkList
    public void list() {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("List is empty!!");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // Add Node at the rail
    public void add(HeroNode2 heroNode) {

        // 找到最后的节点
        HeroNode2 temp = head;
        while (temp != null) {
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        // Add
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // Add Node by no
    public void addNo(HeroNode2 heroNode) {
        if (heroNode.no < 0) {
            System.out.println("Please input the right no");
            return;
        }

        HeroNode2 temp = head;
        boolean flag = false;

        if (head.next == null) {
            System.out.println("List is empty");
        }

        while (true) {
            if (temp.next == null){
                break;
            }
            if (heroNode.no <= temp.next.no ) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        if (flag == true) {
            heroNode.next = temp.next;
            temp.next.next.pre = heroNode;
            temp.next = heroNode.next;
            temp.next.pre = temp;
        }else {
            temp.next = heroNode;
            heroNode.pre = temp;
            heroNode.next = null;
        }
    }

    // Update the value
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("List is empty");
            return;
        }
        //找到需要修改的节点
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            if (temp.next == null) {
                break; // 表示遍历完链表
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

    // Delete a node
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("List is empty");
            return;
        }
        HeroNode2 temp = head;
        // flag: if get the node to delete
        boolean flag = false;

        // Find the node, and delete itself.
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true){
            // Delete ifself
            temp.pre.next = temp.next;
            // if the node is rail
            if (temp.next  != null){
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("There is no NO:%d.node in the list", no);
        }

    }
}

//定义node
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;


    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
