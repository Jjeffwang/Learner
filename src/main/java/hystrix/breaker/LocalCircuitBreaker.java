package hystrix.breaker;

import hystrix.state.CloseState;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/27 下午5:29
 * @Description:
 */
public class LocalCircuitBreaker extends AbstractCircuitBreaker {

    public LocalCircuitBreaker(String failRateForClose,int idleTimeForOpen, String passRateForHalfOpen, int failNumForHalfOpen) {
        this.thresholdFailRateForClose = failRateForClose;
        this.thresholdIdleTimeForOpen = idleTimeForOpen;
        this.thresholdPassRateForHalfOpen = passRateForHalfOpen;
        this.thresholdFailNumForHalfOpen = failNumForHalfOpen;
    }

    @Override
    public void reset() {
        this.setState(new CloseState());
    }

    @Override
    public boolean canPassCheck() {
        return getState().canPassCheck(this);
    }

    @Override
    public void countFailNum() {
        getState().countFailNum(this);
    }
}
