package com.huangyuanlove;

import java.util.List;

/**
 * Created by huangyuan on 16-7-17.
 */
public class TranslateBean {


    private BasicBean basic;
    private String query;
    private int errorCode;
    private List<String> translation;
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
