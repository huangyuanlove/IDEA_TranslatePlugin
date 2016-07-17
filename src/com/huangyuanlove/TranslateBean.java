package com.huangyuanlove;

import java.util.List;

/**
 * Created by huangyuan on 16-7-17.
 */
public class TranslateBean {


    private BasicBean basic;
    /**
     * translation : ["翻译"]
     * basic : {"explains":["vt. 翻译；转化；解释；转变为；调动","vi. 翻译"]}
     * query : translate
     * errorCode : 0
     * web : [{"value":["翻译","转化","协议转换信息"],"key":"translate"},{"value":["线上翻译","平移工具","移动工具"],"key":"Translate Tool"},{"value":["翻译它","翻译","将其翻译"],"key":"translate it"}]
     */

    private String query;
    private int errorCode;
    private List<String> translation;
    /**
     * value : ["翻译","转化","协议转换信息"]
     * key : translate
     */

    private List<WebBean> web;

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        private List<String> explains;
        private String phonetic;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }




        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebBean {
        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }





    }


    @Override
    public String toString() {

        StringBuffer webStringBuffer = new StringBuffer();

        for(int i =0;i<web.size();i++)
        {
            webStringBuffer.append(web.get(i).getKey() +":\t");
            for(int j =0 ;j<web.get(i).getValue().size();j++)
            {
                webStringBuffer.append(web.get(i).getValue().get(j));
            }
            webStringBuffer.append("\n");
        }

        StringBuffer translateBuffer = new StringBuffer();
        for(int i = 0;i<basic.getExplains().size();i++)
        {
            translateBuffer.append(basic.getExplains().get(i) +"\n");
        }



        return query +"\n" + basic.getPhonetic() +"\n" + translateBuffer.toString() +"\n网络释义+\n" + webStringBuffer.toString();
    }
}
