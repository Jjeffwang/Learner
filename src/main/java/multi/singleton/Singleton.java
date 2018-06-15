package multi.singleton;

/**
 * 枚举实现单例
 */
public class Singleton {

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE.getInstance();
    }

    private static enum SingletonInstance {

        INSTANCE;

        private Singleton singleton;

        //JVM会保证此方法绝对只调用一次
        private SingletonInstance() {
            singleton = new Singleton();
        }

        public Singleton getInstance() {
            return singleton;
        }
    }
}
