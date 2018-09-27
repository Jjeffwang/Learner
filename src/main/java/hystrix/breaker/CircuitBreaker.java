package hystrix.breaker;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/27 下午4:35
 * @Description:熔断器接口
 */
public interface CircuitBreaker {

    /**
     * 重置熔断器
     */
    void reset();

    /**
     * 是否允许通过熔断器
     * @return
     */
    boolean canPassCheck();

    /**
     * 统计失败次数
     */
    void countFailNum();
}
