package com.ytfs.sparsearray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author by ytfs
 * @Description 将稀疏数组存入磁盘文件和从磁盘文件还原稀疏数组
 * @Create 2020/8/22-20:37
 */
public class ReadAndSaveSparseArrayToFile{

    /**
     * 将稀疏数组存入磁盘
     * @param sparseArray
     * @return
     */
    public static boolean saveSparseArrayToFile(int[][] sparseArray){
        FileWriter fileWriter = null;
        //result代表是否存入磁盘成功
        boolean result = false;
        File file = new File("F://map.data");
        //文件不存在就创建一个文件
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //打开文件写入
            fileWriter = new FileWriter(file);
            //遍历稀疏数组
            for (int[] ints : sparseArray) {
                fileWriter.write(ints[0] + "\t" + ints[1] + "\t" + ints[2]);
                fileWriter.write("\r");
            }
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭文件写入流
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 从磁盘读取稀疏数组
     * @return
     */
    public static int[][] readSparseArrayToFile(){
        int[][] sparseArray = null;
        //readOneRow用于判断是否为第一行
        boolean readOneRow = false;
        //文件地址
        File file = new File("F:\\map.data");
        BufferedReader bufferedReader = null;
        try {
            //读取文件map.data
            bufferedReader = new BufferedReader(new FileReader(file));
            //获取文件中的每一行,并且每一行不为空
            String line = null;
            //记录稀疏数组应该赋值的行数
            int count = 0;
            //遍历文件中读取到的每一非空行
            while ((line = bufferedReader.readLine()) != null) {
                String[] tempStr = line.split("\t");
                if (!readOneRow){
                    //当前为第一行,那么就创建一个稀疏数组,稀疏数组的行数 = 有效值个数 + 1
                    sparseArray = new int[Integer.parseInt(tempStr[2]) + 1][3];
                    sparseArray[count][0] = Integer.parseInt(tempStr[0]);
                    sparseArray[count][1] = Integer.parseInt(tempStr[1]);
                    sparseArray[count][2] = Integer.parseInt(tempStr[2]);
                    count++ ;
                    readOneRow = true;
                }else{
                    //当前不是文件中的第一行,就直接给稀疏数组赋值
                    sparseArray[count][0] = Integer.parseInt(tempStr[0]);
                    sparseArray[count][1] = Integer.parseInt(tempStr[1]);
                    sparseArray[count][2] = Integer.parseInt(tempStr[2]);
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("还原的稀疏数组：");
        for (int i = 0;  i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        return sparseArray;
    }
}
