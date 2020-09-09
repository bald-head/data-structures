package com.ytfs.queue;

import java.util.Scanner;

/**
 * @author by ytfs
 * @Description 环形的队列--用数组模拟的
 * @Create 2020/8/23-22:15
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建h环形队列并且队列长度为3
        CircleArrayQueue queue = new CircleArrayQueue(3);
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

/**
 * 数组模拟环形队列
 */
class CircleArrayQueue {

    private int maxSize;  //队列的最大长度

    private int front;      //队列的头

    private int rear;       //队列的尾的最后一个元素的后一个位置。因为希望空出一个作为约定

    private int[] arr;      //用来存放数据，模拟队列

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];     //初始化数组

        front = 0;

        rear = 0;
    }

    /**
     * 用来判断环形队列是否满的状态
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
     * 向队列添加一个元素
     *
     * @param number
     */
    public void addQueue(int number) {
        if (isFull()) {
            new RuntimeException("队列已满，不能添加元素");
        }
        arr[rear] = number;
        //将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 从队列中取出一个元素
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            new RuntimeException("当前队列为空，不能取出数据");
        }
        /**
         * front 指向队列的第一个元素
         * 1、先吧front保存在一个临时变量
         * 2、将front后移，考虑取模
         * 3、将临时保存的变量返回
         */

        int val = arr[front];
        front = (front + 1) % maxSize;
        return val;
    }

    /**
     * 取出队列头的数据
     *
     * @return
     */
    public void showQueue() {
        if (isEmpty()) {
            //当前队列为空，不能取出数据
            System.out.println("队列为空，没有数据·····");
        }
        //思路：从front开始遍历，遍历多少个元素呢
        for (int i = front; i < size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列的有效数据的个数
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 取出队列头元素
     *
     * @return
     */
    public int headQueue() {
        if (isEmpty()) {
            new RuntimeException("队列为空，不能取出数据·····");
        }
        return arr[front];
    }
}
