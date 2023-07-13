package com.example.crazymath.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.crazymath.R;
import com.example.crazymath.view.OnMainCallBack;
import com.example.crazymath.view.fragment.BaseFragment;

import java.lang.reflect.Constructor;

public abstract class BaseActivity<T extends ViewBinding, M extends ViewModel> extends AppCompatActivity
        implements View.OnClickListener, OnMainCallBack {
    protected T binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initBinding();
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract Class<M> getClassViewModel();

    protected abstract void initViews();

    protected abstract T initBinding();

    @Override
    public final void onClick(View v) {
        clickViews(v);
    }

    protected void clickViews(View v) {
        // do nothing
    }

    protected void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFragment(String tag, Object data, boolean isBack) {

        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) cons.newInstance();
            fragment.setCallBack(this);
            fragment.setData(data);

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ln_main, fragment, tag);
            if (isBack){
                trans.addToBackStack(null);
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
