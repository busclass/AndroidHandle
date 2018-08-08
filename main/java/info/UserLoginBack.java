package info;

import java.util.List;

/**
 * Created by Administrator on 2018/08/07.
 */

public class UserLoginBack {



    public class Bean
    {
        private List<String> column;

        private String createTime;

        private int id;

        private String loginName;

        private String name;

        private int organizationId;

        private String organizationName;

        private String password;

        private int state;

        private List<String> url;

        public void setColumn(List<String> column){
            this.column = column;
        }
        public List<String> getColumn(){
            return this.column;
        }
        public void setCreateTime(String createTime){
            this.createTime = createTime;
        }
        public String getCreateTime(){
            return this.createTime;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setLoginName(String loginName){
            this.loginName = loginName;
        }
        public String getLoginName(){
            return this.loginName;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setOrganizationId(int organizationId){
            this.organizationId = organizationId;
        }
        public int getOrganizationId(){
            return this.organizationId;
        }
        public void setOrganizationName(String organizationName){
            this.organizationName = organizationName;
        }
        public String getOrganizationName(){
            return this.organizationName;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public String getPassword(){
            return this.password;
        }
        public void setState(int state){
            this.state = state;
        }
        public int getState(){
            return this.state;
        }
        public void setUrl(List<String> url){
            this.url = url;
        }
        public List<String> getUrl(){
            return this.url;
        }
    }


    public   class Root
    {
        private int result;

        private Bean bean;

        public void setResult(int result){
            this.result = result;
        }
        public int getResult(){
            return this.result;
        }
        public void setBean(Bean bean){
            this.bean = bean;
        }
        public Bean getBean(){
            return this.bean;
        }
    }



}
