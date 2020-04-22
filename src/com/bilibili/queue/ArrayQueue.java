package com.bilibili.queue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args){

        //A new Queue
        ArrQueue arrayQueue = new ArrQueue(3);
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

// 使用数组模拟队列
class ArrQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr; //用于存放数据

    //创建队列的构造器
    public  ArrQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; //指向Queue头部，并不包含， 头部的前一个位置
        rear = -1; //指向Queue尾部，包含， 尾部的最后一个数据
    }

    //判断队列是不是满了
    public boolean isFull(){
        return rear == maxSize -1;
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
        rear++; //把屁股往后挪
        arr[rear] = n;
    }

    //获取队列数据
    public int deQueue(){
        //队列空的么？
        if (isEmpty()){
            //抛出异常处理
            throw new RuntimeException("Queue is empty!!!");
        }
        front++;
        return arr[front];
    }

    //打印数据
    public void printQueue(){
        if (isEmpty()){
            System.out.println("Queue is Empty");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i, arr[i]);
        }
    }

    //显示队列的头
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("Queue is Empty");
        }
        return arr[front+1];
    }

}