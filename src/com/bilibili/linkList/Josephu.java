package com.bilibili.linkList;

public class Josephu {
    public static void main(String[] args) {
        // Test
        CircleSingleLinkList circleSingleLinkList = new CircleSingleLinkList();
        circleSingleLinkList.addBoy(5);

        circleSingleLinkList.countBoy(1,2,5);
    }
}

// Define Node
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }
}


// Define circle linklist
class CircleSingleLinkList {
    // Init the first node
    private Boy first = new Boy(-1);

    // Add Node to create a circle linklist
    public void addBoy(int nums) {
        // check nums
        if (nums < 1) {
            System.out.println("Invaild nums");
            return;
        }

        Boy curBoy = null;
        for (int i=1; i<=nums; i++){
            Boy boy = new Boy(i);
            // The first boy
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // traverse
    public void travesal() {
        if (first == null) {
            System.out.println("Empty ...");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("%d \n", curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }else {
                curBoy = curBoy.getNext();
            }
        }
    }

    // Josephu computation
    public void countBoy(int startNo, int countNum, int nums) {
        // check input parameters
        if(first == null || startNo < 1 || startNo > nums) {
            System.out.println("Parameters Error");
            return;
        }
        // create helper and set it to the tail
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // move helper and first for k-1 times
        for (int j=0; j<startNo-1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // While counting, first and helper move m-1 times, until only one
        // node left
        while (true) {
            // judge if only one node left
            if (helper == first){
                break;
            }
            // move first and helper at the same time for countNum-1 times
            for (int j=0; j<countNum-1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("%d \n", first.getNo());
    }
}