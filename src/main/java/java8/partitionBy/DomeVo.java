package java8.partitionBy;

public class DomeVo {

    private int code;
    private boolean type;


    public DomeVo(int code, boolean type) {
        this.code = code;
        this.type = type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean getType() {
        return type;
    }

    public int getCode() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
