package no.asmadsen.exam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import rx.Observable;

import javax.inject.Named;
import java.util.List;

@Named
public class Utils {
    public <T> List<Iterable<T>> groupIntoRows(Iterable<T> input) {
        return Observable.from(input)
                  .buffer(3).map(list -> (Iterable<T>)list)
                 .toList()
                 .toBlocking().first();
    }

    public String toCSV(Iterable<? extends CharSequence> iterable) {
        return String.join(",", iterable);
    }
}
