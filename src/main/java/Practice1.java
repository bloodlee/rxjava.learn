import com.google.common.collect.Lists;
import rx.Observable;

import java.util.ArrayList;

/**
 * Created by yli on 11/24/2016.
 */
public class Practice1 {
  public static void main(String[] args) {
    // simple subscriber.
    Observable<String> howdy = Observable.just("Howdy!");
    howdy.subscribe(System.out::println);

    // overload Observable.just()
    Observable.just("Hello", "World").subscribe(System.out::println);

    ArrayList<String> words = Lists.newArrayList("hello", "world", "again");
    // use a list
    Observable.just(words).subscribe(System.out::println);

    // use Observable.from()
    Observable.from(words).subscribe(System.out::println);

    // use range(i,n) and zip()
    Observable.from(words)
        .zipWith(Observable.range(1, Integer.MAX_VALUE), (aWord, index) -> String.format("%2d. %s", index, aWord))
        .subscribe(System.out::println);

    // split the words
    Observable.from(words)
        .flatMap(word -> Observable.from(word.split("")))
        .distinct()
        .sorted()
        .zipWith(Observable.range(1, Integer.MAX_VALUE), (string, count) -> String.format("%2d. %s", count, string))
        .subscribe(System.out::println);
  }
}
