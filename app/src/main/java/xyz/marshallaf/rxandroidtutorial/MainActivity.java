package xyz.marshallaf.rxandroidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an observable that will emit a sequence of integers
        Observable observable = Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.newThread());

        // create an observer that will subscribe to our observable
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(LOG_TAG, "onSubscribe called on thread " + Thread.currentThread().getName() + ".");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(LOG_TAG, "onNext: " + integer + " on thread " + Thread.currentThread().getName());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(LOG_TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(LOG_TAG, "onComplete called on thread " + Thread.currentThread().getName() + ". Observable is finished emitting!");
            }
        };

        // subscribe the observer to the observable
        observable.subscribe(observer);
    }
}
