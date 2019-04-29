package hystrix.state;

import hystrix.breaker.AbstractCircuitBreaker;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/27 下午5:34
 * @Description:
 */
public class OpenState implements State{

    /**
     * 进入当前状态的初始化时间
     */
    private long stateTime = System.currentTimeMillis();

    @Override
    public String getStateName() {
        // 获取当前状态名称
        return this.getClass().getSimpleName();
    }

    @Override
    public void checkAndSwitchState(AbstractCircuitBreaker cb) {
        // 打开状态，检查等待时间是否已到，如果到了就切换到半开状态
        long now = System.currentTimeMillis();
        long idleTime = cb.thresholdIdleTimeForOpen * 1000L;
        if (stateTime + idleTime <= now){
            cb.setState(new HalfOpenState());
        }
    }

    @Override
    public boolean canPassCheck(AbstractCircuitBreaker cb) {
        // 检测状态
        checkAndSwitchState(cb);
        return false;
    }

    @Override
    public void countFailNum(AbstractCircuitBreaker cb) {
        // nothing
    }
}
