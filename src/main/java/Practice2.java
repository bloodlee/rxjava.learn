import rx.Observable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by yli on 11/24/2016.
 */
public class Practice2 {

  private static long start = System.currentTimeMillis();

  public static Boolean isSlowTickTime() {
    return (System.currentTimeMillis() - start) % 30_000 >= 15_000;
  }

  public static void main(String[] args) throws InterruptedException {
    Observable<Long> fast = Observable.interval(1, TimeUnit.SECONDS);
    Observable<Long> slow = Observable.interval(3, TimeUnit.SECONDS);

    Observable<Long> clock =
        Observable.merge(
            slow.filter(tick -> isSlowTickTime()),
            fast.filter(tick -> !isSlowTickTime())
        );

    clock.subscribe(tick -> System.out.println(new Date()));

    Thread.sleep(60_000);
  }

}
