package com.acezhhh.converter.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.acezhhh.converter.ConverterApplication;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import org.apache.commons.lang.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author ace
 * @date 2022/1/6
 */
@FXMLController
public class IndexController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextArea inputText;

    @FXML
    void toUpperCase(ActionEvent event) {
        if (!validateTextArea()) {
            return;
        }
        inputText.setText(inputText.getText().toUpperCase());
    }

    @FXML
    void toLowerCase(ActionEvent event) {
        if (!validateTextArea()) {
            return;
        }
        inputText.setText(inputText.getText().toLowerCase());
    }

    @FXML
    void toUnderline(ActionEvent event) {
        if (!validateTextArea()) {
            return;
        }
        String text = StrUtil.toUnderlineCase(inputText.getText());
        inputText.setText(text);
    }

    @FXML
    void toHump(ActionEvent event) {
        if (!validateTextArea()) {
            return;
        }
        String text = StrUtil.toCamelCase(inputText.getText());
        inputText.setText(text);
    }


    @FXML
    void toJsonFormat(ActionEvent event) {
        if (!validateTextArea()) {
            return;
        }
        String text = inputText.getText();
        if (!JSONUtil.isJson(text)) {
            showAlert("输入本文不是json格式!");
        }
        String pretty = text;
        Object obj = JSONObject.parse(text);
        if (obj instanceof JSONArray) {
            JSONArray object = JSONArray.parseArray(text);
            pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
        }
        if (obj instanceof JSONObject) {
            JSONObject object = JSONObject.parseObject(text);
            pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
        }
        inputText.setText(pretty);
    }

    public boolean validateTextArea() {
        return StringUtils.isNotBlank(inputText.getText());
    }

    public void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, new ButtonType("确定", ButtonBar.ButtonData.YES));
        alert.setHeaderText(null);
        alert.initOwner(ConverterApplication.getStage());
        alert.showAndWait();
    }

}
