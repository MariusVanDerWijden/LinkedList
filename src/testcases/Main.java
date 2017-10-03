package testcases;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by matematik on 3/28/17.
 */
public class Main {

    public static void main(String[] args){
        checkTestCases(1000);
    }

    private static void checkTestCases(int testSize){
        //System.out.println(checkCorrectness(testSize));
        //checkPerformance(testSize);
        //System.out.println(checkCorArrayList(testSize));
        //System.out.println(checkAddAllConstructor(testSize));
        System.out.println(checkIterator(testSize));
    }

    private static void checkPerformance(int testSize){
        int iterations = 50000;
        int testsize = 1000;
        double d = 1.0;

        for(int q = 0; q < testsize; q++){
            d += checkAdd(iterations);
        }
        d = d/testsize;

        double b = 1.0;
        for(int q = 0; q < testsize; q++){
            b += checkAddAll(iterations);
        }
        b = b/testsize;

        System.out.println("D: "+d+" b: "+b );
        //System.out.println(checkAdd(2000));
    }

    private static boolean checkCorrectness(int testSize)
    {
        boolean equal;
        test.LinkedList<String> newList = generateNewLinkedListVar(testSize,"asdf");
        test.LinkedList<String> newList2 = generateNewLinkedListVar(testSize,"abab");
        java.util.LinkedList<String> list = generateStdLinkedListVar(testSize,"asdf");
        java.util.LinkedList<String> list2 = generateStdLinkedListVar(testSize,"abab");
        equal = checkEqual(list,newList);
        list.addAll(list2);
        newList.addAll(newList2);
        return equal && checkEqual(list,newList);
    }

    private static boolean checkCorArrayList(int testSize){
        boolean equal;
        java.util.ArrayList<String> addList = generateStdArrayList(testSize,"tttt");
        test.LinkedList<String> newList = generateNewLinkedListVar(testSize,"asdf");
        test.LinkedList<String> newList2 = generateNewLinkedListVar(testSize,"abab");
        java.util.LinkedList<String> list = generateStdLinkedListVar(testSize,"asdf");
        java.util.LinkedList<String> list2 = generateStdLinkedListVar(testSize,"abab");
        equal = checkEqual(list,newList);
        list.addAll(addList);
        newList.addAll(addList);
        equal = equal && checkEqual(list,newList);
        list.addAll(list2);
        newList.addAll(newList2);
        return equal && checkEqual(list,newList);
    }

    private static double checkAdd(int testSize){

        long l3 = System.nanoTime();
        test.LinkedList<Object> newList = generateNewLinkedListConst(testSize,"asdf");
        long l4 = System.nanoTime();

        long l1 = System.nanoTime();
        java.util.LinkedList<Object> list = generateStdLinkedListConst(testSize,"asdf");
        long l2 = System.nanoTime();

        double timeNewList = l4-l3;
        double timeUtilList = l2-l1;

        boolean equal = checkEqual(list,newList);
        return equal?timeNewList/timeUtilList:Double.NaN;
    }

    private static double checkAddAllConstructor(int testSize){
        test.LinkedList<Object> newList = generateNewLinkedListConst(testSize,"asdf");
        java.util.LinkedList<Object> list = generateStdLinkedListConst(testSize,"asdf");
        long l3 = System.nanoTime();
        test.LinkedList<Object> newList2 = new test.LinkedList<>(newList);
        long l4 = System.nanoTime();
        long l1 = System.nanoTime();
        java.util.LinkedList<Object> list2 = new java.util.LinkedList<>(list);
        long l2 = System.nanoTime();

        double timeNewList = l4-l3;
        double timeUtilList = l2-l1;

        boolean equal = checkEqual(list2,newList2);
        return equal?timeNewList/timeUtilList:Double.NaN;
    }

    //TODO complete this
    private static boolean checkIterator(int testSize){

        test.LinkedList<String> newList = generateNewLinkedListVar(testSize,"asdf");
        java.util.LinkedList<String> list = generateStdLinkedListVar(testSize,"asdf");
        boolean equal = newList.size() == list.size();
        for(String s: newList){
            equal = equal && list.contains(s);
        }
        System.out.println(equal);
        Iterator<String> newIter = newList.iterator();
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()||newIter.hasNext()){
            equal = equal && iter.next().equals(newIter.next());
        }

        java.util.ArrayList<String> addList = generateStdArrayList(testSize,"tttt");
        list.addAll(addList);
        newList.addAll(addList);

        newIter = newList.iterator();
        iter = list.iterator();

        while (iter.hasNext()||newIter.hasNext()){
            equal = equal && iter.next().equals(newIter.next());
        }


        test.LinkedList<String> newList2 = generateNewLinkedListVar(testSize,"abab");
        java.util.LinkedList<String> list2 = generateStdLinkedListVar(testSize,"abab");

        newList.addAll(newList2);
        list.addAll(list2);

        newIter = newList.listIterator();
        iter = list.listIterator();

        while (iter.hasNext()||newIter.hasNext()){
            equal = equal && iter.next().equals(newIter.next());
        }
        newIter = newList.listIterator();
        iter = list.listIterator();
        boolean bool = false;
        while (iter.hasNext()||newIter.hasNext()){
            String s = "a" ,q = "a";
            try {
                s = iter.next();
            }catch (ConcurrentModificationException e){
                try {
                    q = newIter.next();
                }catch (ConcurrentModificationException w){
                    System.out.println("ConcurrenModificationException");
                    break;
                }
                System.out.println("ERROR: should never happen");
            }
            equal = equal && q.equals(s);
            if(!bool){
                bool = true;
                //iter.remove();
                //newIter.remove();
                try {
                    list.remove();
                }catch (ConcurrentModificationException e){
                    e.printStackTrace();
                }
                try {
                    newList.remove();
                }catch (ConcurrentModificationException e){
                    e.printStackTrace();
                }
            }
        }

        return equal;
    }

    private static double checkAddAll(int testSize){
        java.util.LinkedList<Object> list = generateStdLinkedListConst(testSize,"asdf");
        java.util.LinkedList<Object> list2 = generateStdLinkedListConst(testSize,"asdf2");
        long l1 = System.nanoTime();
        list.addAll(list2);
        long l2 = System.nanoTime();


        test.LinkedList<Object> newList = generateNewLinkedListConst(testSize,"asdf");
        test.LinkedList<Object> newList2 = generateNewLinkedListConst(testSize,"asdf2");
        long l3 = System.nanoTime();
        newList.addAll(newList2);
        long l4 = System.nanoTime();

        double timeNewList = l4-l3;
        double timeUtilList = l2-l1;
        boolean equal = checkEqual(list,newList);
        return equal?timeNewList/timeUtilList:Double.NaN;
    }

    private static java.util.LinkedList<Object> generateStdLinkedListConst(int testSize, Object element){
        java.util.LinkedList<Object> list = new java.util.LinkedList<>();
        for(int i = 0; i < testSize; i++){
            list.add(element);
        }
        return list;
    }

    private static test.LinkedList<Object> generateNewLinkedListConst(int testSize, Object element){
        test.LinkedList<Object> list = new test.LinkedList<>();
        for(int i = 0; i < testSize; i++){
            list.add(element);
        }
        return list;
    }

    private static test.LinkedList<String> generateNewLinkedListVar(int testSize, String base){
        test.LinkedList<String> list = new test.LinkedList<>();
        for(int i = 0; i < testSize; i++){
            list.add(base + i);
        }
        return list;
    }

    private static java.util.LinkedList<String> generateStdLinkedListVar(int testSize, String base){
        java.util.LinkedList<String> list = new java.util.LinkedList<>();
        for(int i = 0; i < testSize; i++){
            list.add(base + i);
        }
        return list;
    }

    private static java.util.ArrayList<String> generateStdArrayList(int testSize, String base){
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        for(int i = 0; i < testSize; i++){
            list.add(base + i);
        }
        return list;
    }

    private static boolean checkEqual(
            java.util.LinkedList<? extends Object> list, test.LinkedList<? extends Object> newList){
        boolean equal = newList.peek().equals(list.peek());
        for(int i = 0; i < newList.size(); i++){
            equal = equal && newList.get(i).equals(list.get(i));
            if(!equal)
                System.out.println("tick: "+i+"   "+newList.get(i) + "   "+list.get(i));
        }
        return equal;
    }
}
