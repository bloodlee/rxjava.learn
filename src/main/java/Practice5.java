import common.CreateObservable;
import rx.Observable;
import rx.Observer;
import rx.observables.ConnectableObservable;

import java.io.InputStream;
import java.util.regex.Pattern;

import static rx.Observable.from;

/**
 * Created by yli on 12/5/2016.
 */
public class Practice5 {

  public static final class ReactiveSum implements Observer<Double> {
    private double sum;

    public ReactiveSum(Observable<Double> a, Observable<Double> b) {
      this.sum = 0.0;

      Observable.combineLatest(a, b, (v1, v2) -> {
        return v1 + v2;
      }).subscribe(this);
    }

    @Override
    public void onCompleted() {
      System.out.println("Exiting last sum was : " + this.sum);
    }

    @Override
    public void onError(Throwable e) {
      System.err.println("Go an error!");
      e.printStackTrace();
    }

    @Override
    public void onNext(Double aDouble) {
      this.sum = aDouble;
      System.out.println("update : a + b = " + sum);
    }
  }

  public static void main(String[] args) {
    ConnectableObservable<String> input = CreateObservable.from(System.in);

    Observable<Double> a = varStream("a", input);
    Observable<Double> b = varStream("b", input);

    ReactiveSum sum = new ReactiveSum(a, b);

    input.connect();
  }

  private static Observable<Double> varStream(String varName, Observable<String> input) {
    final Pattern pattern = Pattern.compile("^\\s*" + varName + "\\s*[:|=]\\s*(-?\\d+\\.?\\d*)$");

    return
        input.map(str -> pattern.matcher(str))
            .filter(matcher -> matcher.matches() && matcher.group(1) != null)
            .map(matcher -> matcher.group(1))
            .filter(str -> str != null)
            .map(str -> Double.parseDouble(str));
  }
}
