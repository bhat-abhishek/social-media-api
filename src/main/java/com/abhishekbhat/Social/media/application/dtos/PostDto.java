package com.abhishekbhat.Social.media.application.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    @Size(min = 50, message = "Content should contain at least 50 characters")
    private String content;
}
