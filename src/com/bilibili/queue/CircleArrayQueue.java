package com.bilibili.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        System.out.println("测试环形数组");
//A new Queue
        CircleArray arrayQueue = new CircleArray(3);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加队列元素");
            System.out.println("g(get): 获取队列元素");
            System.out.println("h(head): 查看队列头");
            key = scanner.next().charAt(0); //接受一个字符
            switch (key){
                case 's':
                    arrayQueue.printQueue();
                    break;
                case 'a':
                    System.out.println("Output a number");
                    int value = scanner.nextInt();
                    arrayQueue.enQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.deQueue();
                        System.out.printf("Get the value: %d \n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("The head value of Queue: %d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("Exit Programme");
    }
}

class CircleArray{
    private int maxSize;
    // front从指向第一个元素前面的位置，改变成指向第一个元素，而且初始值为0
    private int front;
    //rear从指向最后一个元素，改成最后一个元素的后一个位置
    private int rear;
    //我们约定rear后面的一个位置空置不用
    private int[] arr; //用于存放数据

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断队列是不是满了
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是不是空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void enQueue(int n){
        //队列满了么？
        if (isFull()){
            System.out.println("队列满了，不能加入");
            return;
        }
        arr[rear] = n;
        //把rear后移， 必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列数据
    public int deQueue(){
        //队列空的么？
        if (isEmpty()){
            //抛出异常处理
            throw new RuntimeException("Queue is empty!!!");
        }
        // 需要分析出front是指向队列的第一个元素
        // 1.先把front对应的值保存到一个临时变量
        // 2.把front后移
        // 3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //打印数据
    public void printQueue(){
        if (isEmpty()){
            System.out.println("Queue is Empty");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i % maxSize, arr[i % maxSize]);
        }
    }

    //求当前队列的有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("Queue is Empty");
        }
        return arr[front];
    }
}