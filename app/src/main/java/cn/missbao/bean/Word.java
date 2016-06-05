package cn.missbao.bean;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/6.
 */
public class Word {
    public String _id;
    public String word;
    public String soundmark;
    public String translate;

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", soundmark='" + soundmark + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
