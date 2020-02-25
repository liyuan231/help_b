package com.example.help_b.model;

public class Comment {

  private Integer id;
  private Integer parentId;
  private Integer type;
  private BasicUser basicUser;
//  private Integer commentator;
  private String createdAt;
  private String modifiedAt;
  private Integer praiseCount;
  private String content;

  public void setCommentator(Integer commentator){
    basicUser = new BasicUser();
    basicUser.setId(commentator);
  }

  public void setBasicUser(BasicUser basicUser) {
    this.basicUser = basicUser;
  }

  public Comment(Integer id, Integer parentId, Integer type, BasicUser basicUser, Integer commentator, String createdAt, String modifiedAt, Integer praiseCount, String content) {
    this.id = id;
    this.parentId = parentId;
    this.type = type;
    this.basicUser = basicUser;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.praiseCount = praiseCount;
    this.content = content;
  }

  public Comment(Integer parentId, Integer type, BasicUser basicUser, String createdAt, String modifiedAt, Integer praiseCount, String content) {
    this.parentId = parentId;
    this.type = type;
    this.basicUser = basicUser;
    this.createdAt = createdAt;
    this.modifiedAt = modifiedAt;
    this.praiseCount = praiseCount;
    this.content = content;
  }

  public BasicUser getBasicUser() {
    return basicUser;
  }

  public Comment() {

  }
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }


  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }




  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  public String getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
  }


  public Integer getPraiseCount() {
    return praiseCount;
  }

  public void setPraiseCount(Integer praiseCount) {
    this.praiseCount = praiseCount;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
