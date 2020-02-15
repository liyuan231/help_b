package com.example.help_b.model;

public class Question {
    private long id;
    private String title;
    private String detail;
    private String tags;
    private String createdAt;
    private String modifiedAt;
    private long author;
    private long commentCount;
    private long readCount;
    private long praiseCount;

    public Question() {
    }

    public Question(String title, String detail, String tags, String createdAt, String modifiedAt, long author, long commentCount, long readCount, long praiseCount) {
        this.title = title;
        this.detail = detail;
        this.tags = tags;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.author = author;
        this.commentCount = commentCount;
        this.readCount = readCount;
        this.praiseCount = praiseCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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


    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }


    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }


    public long getReadCount() {
        return readCount;
    }

    public void setReadCount(long readCount) {
        this.readCount = readCount;
    }


    public long getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(long praiseCount) {
        this.praiseCount = praiseCount;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", tags='" + tags + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                ", author=" + author +
                ", commentCount=" + commentCount +
                ", readCount=" + readCount +
                ", praiseCount=" + praiseCount +
                '}';
    }
}
