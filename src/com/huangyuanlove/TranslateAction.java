package com.huangyuanlove;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.event.EditorMouseEventArea;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Key;
import com.intellij.ui.JBColor;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by huangyuan on 16-7-16.
 */
public class TranslateAction extends AnAction {

    private String translateUrl = "http://fanyi.youdao.com/openapi.do?keyfrom=AS-TranslatePlugin&key=346994278&type=data&doctype=json&version=1.1&q=%s";


    public void actionPerformed(AnActionEvent e) {
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        SelectionModel model = mEditor.getSelectionModel();
        final String selectedText = model.getSelectedText();
        if (TextUtils.isEmpty(selectedText)) {
            return;
        }

//        Messages.showMessageDialog(selectedText, "选中的文字", Messages.getInformationIcon());
        TranslateBean translateBean = doTranslate(selectedText);
        showPopupBalloon(mEditor,translateBean.toString());


    }


    private TranslateBean doTranslate(String translateText) {
        Gson gson = new Gson();
        InputStream is = null;
        StringBuffer resultData = new StringBuffer();
        try {
            URL url = new URL(String.format(translateUrl,  URLEncoder.encode(translateText,"utf-8")));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true); //允许输入流，即允许下载
            httpURLConnection.setDoOutput(true); //允许输出流，即允许上传
            httpURLConnection.setUseCaches(false); //不使用缓冲
            httpURLConnection.setRequestMethod("GET"); //使用get请求
            is = httpURLConnection.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine  = "";
            while((inputLine = bufferReader.readLine()) != null){
                resultData.append(inputLine);
            }

            System.out.println(resultData.toString());

            TranslateBean translateBean = gson.fromJson(resultData.toString(), TranslateBean.class);
            return  translateBean;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private void showPopupBalloon(final Editor editor, final String result) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(186, 238, 186), new Color(73, 117, 73)), null)
                        .setFadeoutTime(5000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}
