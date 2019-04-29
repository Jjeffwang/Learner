package java8.partitionBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionByDemo {

    public static void main(String[] args) {

        DomeVo domeVo1 = new DomeVo(1, true);
        DomeVo domeVo2 = new DomeVo(2, false);
        DomeVo domeVo3 = new DomeVo(3, true);
        DomeVo domeVo4 = new DomeVo(4, false);
        List<DomeVo> domeVos =new ArrayList<>();
        domeVos.add(domeVo1);
        domeVos.add(domeVo2);
        domeVos.add(domeVo3);
        domeVos.add(domeVo4);
        Map<Boolean, List<DomeVo>> map=domeVos.stream().collect(Collectors.partitioningBy(DomeVo::getType));
        System.out.println(map.get(true).size());
    }
}
