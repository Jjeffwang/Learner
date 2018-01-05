package enums;

/**
 * Created by ${WangChengYong} on 2018/1/5.
 */
public enum HourlyPayGrade {

    APPRENTICE {
        public double rate() {
            return 1.0;
        }
    },

    JOURNEYMAN {
        public double rate() {
            return 2.0;
        }
    },
    MASTER {
        public double rate() {
            return 3.0;
        }
    };

    public abstract double rate();
}
