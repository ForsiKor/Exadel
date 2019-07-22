package app.store;

import java.util.Map;

public interface IStore <T> {
    void addInfo(Map<Integer, T> type);
    Map<Integer, T> getInfo();
    int getMaxId();
}