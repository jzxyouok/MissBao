package cn.missbao.bean;

/**
 * Role
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class Statistics {
    public String name;
    public int month;
    public int day;
    public int all;

    @Override
    public String toString() {
        return "Statistics{" +
                "name='" + name + '\'' +
                ", month=" + month +
                ", day=" + day +
                ", all=" + all +
                '}';
    }
}
