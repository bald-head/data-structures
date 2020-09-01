package com.ytfs.queue;

/**
 * @author by ytfs
 * @Description 环形的队列--用数组模拟的
 * @Create 2020/8/23-22:15
 */
public class CircleArrayQueue {
    public static void main(String[] args) {

    }

    /**
     * 数组模拟环形队列
     */
    class CircleArray{

        private int maxSize;  //队列的最大长度

        private int front;      //队列的头

        private int rear;       //队列的尾

        private int[] arr;      //用来存放数据，模拟队列

        public CircleArray(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];     //初始化数组

            front = 0;

            rear = 0;
        }

    }
}
