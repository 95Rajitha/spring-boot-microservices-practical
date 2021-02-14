package com.example.movie_info_service.Resources;

import com.example.movie_info_service.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResources
{


    @RequestMapping("/{movieId}")
    public List<Movie> getMovieInfo( @PathVariable("movieId") String movieId){
    return Collections.singletonList( new Movie(movieId,"Transformers") );

    }

}
