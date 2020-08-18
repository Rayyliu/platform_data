package com.platform.dal.model.platform;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Execute implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.case_name
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String caseName;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.description
     *
     * @mbg.generated Mon Aug 17 11:05:05 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.valid
     *
     * @mbg.generated Sun Aug 16 21:48:14 CST 2020
     */
    private Boolean valid;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.interface_id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private Integer interfaceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.project
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String project;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.body
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String body;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.response
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String response;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.case_execute_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String caseExecuteResult;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.assertion_content
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String assertionContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.assert_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String assertResult;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.last_execute_time
     *
     * @mbg.generated Mon Aug 17 13:11:43 CST 2020
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastExecuteTime;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.last_execute_user
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private String lastExecuteUser;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.create_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private Date createAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column execute.update_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table execute
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.id
     *
     * @return the value of execute.id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.id
     *
     * @param id the value for execute.id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.case_name
     *
     * @return the value of execute.case_name
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.case_name
     *
     * @param caseName the value for execute.case_name
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.description
     *
     * @return the value of execute.description
     *
     * @mbg.generated Mon Aug 17 11:05:05 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.description
     *
     * @param description the value for execute.description
     *
     * @mbg.generated Mon Aug 17 11:05:05 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.valid
     *
     * @return the value of execute.valid
     *
     * @mbg.generated Sun Aug 16 21:48:14 CST 2020
     */
    public Boolean getValid() {
        return valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.valid
     *
     * @param valid the value for execute.valid
     *
     * @mbg.generated Sun Aug 16 21:48:14 CST 2020
     */
    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.interface_id
     *
     * @return the value of execute.interface_id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public Integer getInterfaceId() {
        return interfaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.interface_id
     *
     * @param interfaceId the value for execute.interface_id
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.project
     *
     * @return the value of execute.project
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getProject() {
        return project;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.project
     *
     * @param project the value for execute.project
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.body
     *
     * @return the value of execute.body
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.body
     *
     * @param body the value for execute.body
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.response
     *
     * @return the value of execute.response
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getResponse() {
        return response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.response
     *
     * @param response the value for execute.response
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.case_execute_result
     *
     * @return the value of execute.case_execute_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getCaseExecuteResult() {
        return caseExecuteResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.case_execute_result
     *
     * @param caseExecuteResult the value for execute.case_execute_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setCaseExecuteResult(String caseExecuteResult) {
        this.caseExecuteResult = caseExecuteResult == null ? null : caseExecuteResult.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.assertion_content
     *
     * @return the value of execute.assertion_content
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getAssertionContent() {
        return assertionContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.assertion_content
     *
     * @param assertionContent the value for execute.assertion_content
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setAssertionContent(String assertionContent) {
        this.assertionContent = assertionContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.assert_result
     *
     * @return the value of execute.assert_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getAssertResult() {
        return assertResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.assert_result
     *
     * @param assertResult the value for execute.assert_result
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setAssertResult(String assertResult) {
        this.assertResult = assertResult == null ? null : assertResult.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.last_execute_time
     *
     * @return the value of execute.last_execute_time
     *
     * @mbg.generated Mon Aug 17 13:11:43 CST 2020
     */
    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.last_execute_time
     *
     * @param lastExecuteTime the value for execute.last_execute_time
     *
     * @mbg.generated Mon Aug 17 13:11:43 CST 2020
     */
    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.last_execute_user
     *
     * @return the value of execute.last_execute_user
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public String getLastExecuteUser() {
        return lastExecuteUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.last_execute_user
     *
     * @param lastExecuteUser the value for execute.last_execute_user
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setLastExecuteUser(String lastExecuteUser) {
        this.lastExecuteUser = lastExecuteUser == null ? null : lastExecuteUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.create_at
     *
     * @return the value of execute.create_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.create_at
     *
     * @param createAt the value for execute.create_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column execute.update_at
     *
     * @return the value of execute.update_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column execute.update_at
     *
     * @param updateAt the value for execute.update_at
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table execute
     *
     * @mbg.generated Sun Aug 16 16:57:01 CST 2020
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", caseName=").append(caseName);
        sb.append(", description=").append(description);
        sb.append(", valid=").append(valid);
        sb.append(", interfaceId=").append(interfaceId);
        sb.append(", project=").append(project);
        sb.append(", body=").append(body);
        sb.append(", response=").append(response);
        sb.append(", lastExecuteTime=").append(lastExecuteTime);
        sb.append(", caseExecuteResult=").append(caseExecuteResult);
        sb.append(", assertionContent=").append(assertionContent);
        sb.append(", assertResult=").append(assertResult);
        sb.append(", lastExecuteUser=").append(lastExecuteUser);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}