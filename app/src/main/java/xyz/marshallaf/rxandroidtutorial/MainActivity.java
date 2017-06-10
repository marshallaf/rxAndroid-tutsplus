package xyz.marshallaf.rxandroidtutorial;

import android.app.Activity;
import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
