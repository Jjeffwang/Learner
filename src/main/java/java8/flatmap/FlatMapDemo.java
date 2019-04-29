package java8.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapDemo {

    public static void main(String[] args) {
        //给定列表[1,2,3]和[4,5]====>[(1,4),(1,5),.....]
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        final List<Integer> numbers2 = Arrays.asList(4, 5);
        List<int[]> pairs = numbers1.stream().flatMap(i ->
                numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(pairs.size());

        List<int[]> pairsU = numbers1.stream().flatMap(i ->
                numbers2.stream().filter(j -> (i + j) % 2 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        System.out.println(pairsU.size());
    }
}
