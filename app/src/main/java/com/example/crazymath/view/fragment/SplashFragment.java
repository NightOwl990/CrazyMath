package com.example.crazymath.view.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.crazymath.databinding.FragmentSplashBinding;
import com.example.crazymath.viewmodel.CommonVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, CommonVM> {
    public static final String TAG = SplashFragment.class.getName();

    @Override
    protected FragmentSplashBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentSplashBinding.inflate(inflater, container, false);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::goToMainScreen, 2000);
    }

    private void goToMainScreen() {
        callBack.showFragment(MainFragment.TAG, null, false);
    }
}
