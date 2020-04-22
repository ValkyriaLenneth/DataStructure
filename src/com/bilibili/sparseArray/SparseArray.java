package com.bilibili.sparseArray;

public class SparseArray {

    public static void main(String[] args) {
        // Create a original bi-dim array 11*11
        //0: 没棋子。 1：黑色 2：蓝色
        int chessArray1[][] = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        // print original array
        System.out.println("原始二维数组");
        for (int[] row : chessArray1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 转换成spareArray
        // 1. 先遍历二维数组， 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //创建sparseArray
        int sparseArray[][] = new int[sum + 1][3];
        //给sparseArray赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        //遍历二维数组，把非零的值存在sparseArray里
        int count = 0; //用于记录是第几个非零数据

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray1[i][j];
                }
            }
        }

        //输出sparseArray
        System.out.println();
        System.out.println("SparseArray:");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        System.out.println();

        //SparseArray -> Array
        //1.先读取sparseArray的第一行，来创建一个二维数组
        int chessArray2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        //2.把后面的行还原到Array
        for (int i = 1; i < sparseArray.length ; i++) {
            chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //打印回复的二维数组
        System.out.println("还原的二维数组");
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
