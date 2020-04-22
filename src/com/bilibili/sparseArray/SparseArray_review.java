package com.bilibili.sparseArray;
import java.io.*;

public class SparseArray_review {
    public static void main(String[] args) throws IOException{
        // 创建一个原始二维数组
        int chessArr1[][] = new int[11][11];
        chessArr1[5][4] = 1;
        chessArr1[6][2] = 2;
        chessArr1[7][10] = 3;
        System.out.println("Original ChessArr");
        for(int[] row: chessArr1){
            for(int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //ChessArr -> SparseArr
        //1.根据原来的ChessArr来构造SparseArr的第一行，也就是size和sum
        int sum = 0;
        for (int[] row: chessArr1){
            for(int data: row){
                if (data != 0){
                    sum++;
                }
            }
        }
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //2.再次遍历ChessArr来把每个非0值放进去
        int n_row = 1;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0){
                    sparseArr[n_row][0] = i;
                    sparseArr[n_row][1] = j;
                    sparseArr[n_row][2] = chessArr1[i][j];
                    n_row++;
                }
            }
        }
        //3.把SparseArr存放到某个文件
        File file = new File("H://Java//DataStructure//output//SparseArr.txt");
        FileWriter out = new FileWriter(file);
        for (int[] row: sparseArr){
            for(int data: row){
                out.write(data + "\t");
            }
            out.write("\r\n");
        }
        out.close();

        //SparseArr -> Array
        //1.从文件中读取数据
        FileReader fr = new FileReader("H://Java//DataStructure//output//SparseArr.txt");
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        int[][] buffer = new int[sum+1][3];
        String[] split;
        int rows = 0;

        while ((line = br.readLine()) != null){
            split = line.split("\t");
            for (int i=0; i<split.length; i++){
                buffer[rows][i] = Integer.parseInt(split[i]);
            }
            rows++;
        }
        //2.把buffer还原，首先由第一行创建相同大小的原始数组
        int[][] chessArr2 = new int[buffer[0][0]][buffer[0][1]];
        //3.然后遍历buffer，由之后的每一行，还原数据
        for (int i = 1; i < buffer.length; i++) {
            chessArr2[buffer[i][0]][buffer[i][1]] = buffer[i][2];
        }
        System.out.println("Recovered ChessArr");
        for(int[] row: chessArr2){
            for(int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


    }
}

