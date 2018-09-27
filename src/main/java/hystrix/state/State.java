package hystrix.state;

import hystrix.breaker.AbstractCircuitBreaker;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/27 下午4:38
 * @Description:熔断器状态
 */
public interface State {
    /**
     * 获取当前状态名称
     */
    String getStateName();

    /**
     * 检查以及校验当前状态是否需要扭转
     */
    void checkAndSwitchState(AbstractCircuitBreaker cb);

    /**
     * 是否允许通过熔断器
     */
    boolean canPassCheck(AbstractCircuitBreaker cb);

    /**
     * 统计失败次数
     */
    void countFailNum(AbstractCircuitBreaker cb);
}
