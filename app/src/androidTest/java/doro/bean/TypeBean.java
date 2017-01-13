package doro.bean;

/**
 * Created by qiang.zhang on 2017/1/12.
 */
public class TypeBean {
    private String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String type;

    @Override
    public String toString() {
        return "TypeBean{" +
                "number='" + number + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
