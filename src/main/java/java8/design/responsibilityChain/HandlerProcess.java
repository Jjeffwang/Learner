package java8.design.responsibilityChain;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class HandlerProcess {
    private static String hander(String str) {
        UnaryOperator<String> handerProcess = (String s) -> s +"heLLo";
        UnaryOperator<String> replaceProcess = s -> s.replaceAll("L", "l");
        Function<String, String> pipeline = handerProcess.andThen(replaceProcess);
        return pipeline.apply(str);
    }

    public static void main(String[] args) {
        String str="pLay a fun game LLLL";
        System.out.println(hander(str));
    }
}
