package com.acezhhh.converter;

import com.acezhhh.converter.view.IndexView;
import com.acezhhh.converter.view.LoadingView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConverterApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(ConverterApplication.class, IndexView.class, new LoadingView(), args);
    }

}
