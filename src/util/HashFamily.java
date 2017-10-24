package util;

/**
 * hash函数簇
 * Created by lee on 2017/7/25.
 */
public interface HashFamily <T> {
    int hash(T x, int which);
    int getNumberOfFunctions();
    void generateNewFunctions();

}
