package com.powerzhou.dogstudy.uimodule.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.powerzhou.dogstudy.R;
import com.powerzhou.dogstudy.rxbus.RxBus;
import com.powerzhou.dogstudy.uimodule.base.BaseActivity;
import com.powerzhou.dogstudy.uimodule.widget.EmptyLayout;
import com.powerzhou.dogstudy.util.RxHelper;

import butterknife.BindView;
import rx.Subscriber;

public class BeginActivity extends BaseActivity {

    @BindView(R.id.btn_next)
    Button next_step;

    private boolean mIsSkip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_begin;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        RxHelper.countdown(5)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        _doSkip();
                    }

                    @Override
                    public void onError(Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        next_step.setText(String.format(getString(R.string.next_step),integer.toString()));
                    }
                });
        next_step.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _doSkip();
            }
        });
    }

    private void _doSkip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    protected void updateViews() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {

    }
}
