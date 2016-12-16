import com.google.common.collect.Lists;
import rx.Observable;

/**
 * Created by yli on 12/4/2016.
 */
public class Practice4 {
  public static void main(String[] args) {

    Observable.from(Lists.newArrayList("a", "b", "c")).subscribe(
        (value) -> {
          if ("b".equals(value)) {
            // throw new IllegalArgumentException();
          }
          System.out.println(value);
        },
        (exception) -> {
          // exception.printStackTrace();
        },
        () -> {
          System.out.println("Finalized!");
        }
    );
  }
}
