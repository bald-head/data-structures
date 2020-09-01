package com.ytfs.queue;

import java.util.Scanner;

/**
 * @author by ytfs
 * @Description 用数组模拟队列，并且测试
 * @Create 2020/8/23-16:50
 */
public class ArrayQueueDemo {
    /**
     * 测试队列ArrayQueue
     *
     * @param args
     */
    public static void main(String[] args) {
        //创建队列并且队列长度为3
        ArrayQueue queue = new ArrayQueue(3);
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        char key = ' ';//接收用户输入的字母
        while (loop) {
            System.out.println("a(add):向队列中添加数据");
            System.out.println("s(show):展示队列中的数据");
            System.out.println("g(get):从队列中获取数据");
            System.out.println("e(exit):退出系统");
            System.out.println("h(add):查看队列中的第一个数据");
            key = sc.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("请输入需要添加的数据：");
                    int number = sc.nextInt();
                    queue.addQueue(number);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;
                    break;
                case 'h':
                    int result = 0;
                    try {
                        result = queue.headQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.printf("队列的头部数据为：%d\n", result);
                    break;
                default:
                    break;
            }
        }

    }
}

class ArrayQueue {
    private int maxSize;//队列的最大长度

    private int front;//队列头

    private int rear; //队列尾

    private int[] arr;  //用来存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;//队列的长度

        front = -1;//队列的头，指向队列头数据的前一个位置

        rear = -1;//队列尾，指向队列的最后一个数据的位置

        arr = new int[maxSize];  //初始化haunted队列
    }

    /**
     * 判断队列是否为满的
     *
     * @return
     */
    public boolean isFull() {
        //减一的原因是因为数组的下表是从0开始
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 向队列中添加一个元素
     *
     * @param number
     */
    public void addQueue(int number) {
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        //将指向尾部的指针向后移动一位，并给该位赋值
        rear++;
        arr[rear] = number;
    }

    /**
     * 从队列中取出一个数据
     *
     * @return
     */
    public int getQueue() {
        //判断队列是否为空，空的直接抛出一行
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        //取出队列头部的一个数据
        front++;
        return arr[front];
    }

    /**
     * 查看队列的第一个数据
     *
     * @return
     */
    public int headQueue() {
        //判断队列是否为空，空的直接抛出一行
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据");
        }
        return arr[front + 1];
    }

    /**
     * 展示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()){
            throw new RuntimeException("队列为空，不能取出数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }
}