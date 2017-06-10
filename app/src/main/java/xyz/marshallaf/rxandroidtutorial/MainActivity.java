package xyz.marshallaf.rxandroidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an observable that will emit a sequence of integers
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                // onNext emits each item in the stream
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);

                // call onComplete when the observable has emitted all the items in the sequence
                e.onComplete();
            }
        });

        // create an observer that will subscribe to our observable
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(LOG_TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e(LOG_TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(LOG_TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(LOG_TAG, "onComplete: Observable is finished emitting!");
            }
        };

        // subscribe the observer to the observable
        observable.subscribe(observer);
    }
}
