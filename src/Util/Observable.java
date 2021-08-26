package src.Util;

import java.util.List;
import java.util.ArrayList;

public class Observable {
    private List<Observer> listaDeObservers = new ArrayList<Observer>();

    public Observable getObservable() {
        return this;
    }

    public void addObserver(Observer o) {
        listaDeObservers.add(o);
    }

    public void removeObserver(Observer o) {
        listaDeObservers.remove(o);
    }

    public void notificarObservers() {
        for (Observer o : listaDeObservers)
            o.notificar();
    }
}
