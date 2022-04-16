package com.baldhead.sparsearray;


/**
 * @author by ytfs
 * @Description 二维数组和稀疏数组的互相转换
 * @Create 2020/8/22-16:39
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个二维数组
        int chessArr[][] = new int[11][11];
        //给二维数组赋有效数据
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        System.out.println("原始的二维数组为：");
        //遍历出，二维数组的每一行
        for (int[] row : chessArr) {
            //遍历的到每一行的数据
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //统计原始二维数组中的有效数据的个数,sum代表二维数组中有效数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("有效数据的个数为：" + sum);

        /**
         * 将二维数组转换为稀疏数组
         */
            //1. 创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];

        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //2. 给稀疏数组赋值,count用于计数，计算该稀疏数组的第几行
        int count = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr.length; j++) {
                if (chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        for (int i = 0;  i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }


        /**
         * 将稀疏数组转换为二维数组
         */

        System.out.println("还原后的二维数组如下：");
        int reChessArr[][] = new int[sparseArr[0][1]][sparseArr[0][1]];
        for (int[] row : reChessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //给还原后的二维数组赋值
        for (int i = 1; i < sparseArr.length; i++) {
            reChessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("还原并赋值后的二维数组如下：");
        for (int[] row : reChessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
