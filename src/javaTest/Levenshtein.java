//package javaTest;
//
//import java.util.function.Function;
//
//public class Levenshtein{
//private final Function asList;
// 
//public Levenshtein(Function asList) {
//        this.asList = asList;
//}
// 
//public int distance(T a, T b) {
//// Wagner-Fischer algorithm, with two active rows
// 
//List aList = asList.apply(a);
//List bList = asList.apply(b);
// 
//int bSize = bList.size();
//int[] row0 = new int[bSize + 1];
//int[] row1 = new int[bSize + 1];
// 
//for (int i = 0; i row0[i] = i;
//}
// 
//for (int i = 0; i < bSize; ++i) {
//U ua = aList.get(i);
//row1[0] = row0[0] + 1;
// 
//for (int j = 0; j < bSize; ++j) {
//U ub = bList.get(j);
//int subCost = row0[j] + (ua.equals(ub) ? 0 : 1);
//int delCost = row0[j + 1] + 1;
//int insCost = row1[j] + 1;
//row1[j + 1] = Math.min(subCost, Math.min(delCost, insCost));
//}
// 
//int[] temp = row0;
//row0 = row1;
//row1 = temp;
//}
// 
//return row0[bSize];
//}
//}