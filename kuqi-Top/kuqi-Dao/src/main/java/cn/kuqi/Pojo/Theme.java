package cn.kuqi.Pojo;

public class Theme {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column theme.T_Number
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    private Integer tNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column theme.T_Title
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    private String tTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column theme.T_Url
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    private String tUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column theme.T_UploadTime
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    private String tUploadtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column theme.T_Number
     *
     * @return the value of theme.T_Number
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public Integer gettNumber() {
        return tNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column theme.T_Number
     *
     * @param tNumber the value for theme.T_Number
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public void settNumber(Integer tNumber) {
        this.tNumber = tNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column theme.T_Title
     *
     * @return the value of theme.T_Title
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public String gettTitle() {
        return tTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column theme.T_Title
     *
     * @param tTitle the value for theme.T_Title
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public void settTitle(String tTitle) {
        this.tTitle = tTitle == null ? null : tTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column theme.T_Url
     *
     * @return the value of theme.T_Url
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public String gettUrl() {
        return tUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column theme.T_Url
     *
     * @param tUrl the value for theme.T_Url
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public void settUrl(String tUrl) {
        this.tUrl = tUrl == null ? null : tUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column theme.T_UploadTime
     *
     * @return the value of theme.T_UploadTime
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public String gettUploadtime() {
        return tUploadtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column theme.T_UploadTime
     *
     * @param tUploadtime the value for theme.T_UploadTime
     *
     * @mbg.generated Tue Dec 04 15:47:06 CST 2018
     */
    public void settUploadtime(String tUploadtime) {
        this.tUploadtime = tUploadtime == null ? null : tUploadtime.trim();
    }
}