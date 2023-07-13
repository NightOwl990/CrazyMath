package com.example.crazymath.view.activity;

import android.util.Log;

import com.example.crazymath.databinding.ActivityHomeBinding;
import com.example.crazymath.view.fragment.SplashFragment;
import com.example.crazymath.viewmodel.CommonVM;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, CommonVM> {


    @Override
    public void backToPrevious() {
        onBackPressed();
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected void initViews() {
        showFragment(SplashFragment.TAG, null, false);
        Log.d("ABC", "-------------------------- show");
    }

    @Override
    protected ActivityHomeBinding initBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }
}