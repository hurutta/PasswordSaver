package com.example.PasswordSaver.model;

public class UserData 
{
    private String websiteUrl;
    private String websitePassword;

    public UserData()
    {
        super();
    }

   

    public UserData(String websiteUrl, String websitePassword)
    {
        super();
        this.websiteUrl = websiteUrl;
        this.websitePassword = websitePassword;
    }


    /**
     * @return String return the websiteUrl
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * @param websiteUrl the websiteUrl to set
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * @return String return the websitePassword
     */
    public String getWebsitePassword() {
        return websitePassword;
    }

    /**
     * @param websitePassword the websitePassword to set
     */
    public void setWebsitePassword(String websitePassword) {
        this.websitePassword = websitePassword;
    }

}
