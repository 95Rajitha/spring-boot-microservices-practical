package com.example.movie_rating_service.Resources;

import com.example.movie_rating_service.Model.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingData")
public class RatingResource
{
    @RequestMapping("/{movieId}")
    public Rating getRating( @PathVariable("movieId") String movieId ){
        return new Rating(movieId,4);
    }

}
