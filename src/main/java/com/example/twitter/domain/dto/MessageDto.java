package com.example.twitter.domain.dto;

import com.example.twitter.domain.Message;
import com.example.twitter.domain.User;
import com.example.twitter.domain.util.MessageHelper;
import lombok.Getter;

@Getter
public class MessageDto {
    private Long id;
    private String text;
    private String tag;
    private User author;
    private String filename;
    private Long likes;
    private boolean myLike;


    public MessageDto(Message message, Long likes, boolean myLike) {
        this.id = message.getId();
        this.tag = message.getTag();
        this.text = message.getText();
        this.author = message.getAuthor();
        this.filename = message.getFilename();
        this.likes = likes;
        this.myLike = myLike;
    }

    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                ", author=" + author +
                ", likes=" + likes +
                ", myLike=" + myLike +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTag() {
        return tag;
    }

    public User getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }

    public Long getLikes() {
        return likes;
    }

    public boolean isMyLike() {
        return myLike;
    }
}
