package com.hridyanv.bookmyshow.controllers;

import com.hridyanv.bookmyshow.dtos.BookMovieRequestDto;
import com.hridyanv.bookmyshow.dtos.BookMovieResponseDto;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingController {
    public BookMovieResponseDto BookMovie(BookMovieRequestDto bookMovieRequestDto)
    {
        return new BookMovieResponseDto();
    }
}
