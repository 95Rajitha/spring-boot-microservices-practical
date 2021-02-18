package com.example.movie_catalog_service.resources;

import com.example.movie_catalog_service.models.CatalogItem;
import com.example.movie_catalog_service.models.Movie;
import com.example.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog( @PathVariable("userId")  String userId ){

        List<Rating> ratings = Arrays.asList(
                new Rating( "1234",4),
                new Rating("5678",5)
        );

       return ratings.stream().map( rating ->
       {
           //deprecating the restTemplate to use webClient
//           Movie movie = restTemplate.getForObject( "http://localhost:8081/movies/"+rating.getMovieId(),Movie.class );

          Movie movie = webClientBuilder.build()
                           .get()
                           .uri( "http://localhost:8081/movies/"+rating.getMovieId() )
                           .retrieve()
                           .bodyToMono( Movie.class )
                           .block();
            //.get that refers to what the call is going to d
           //url url that u need to access
           //.retrieve go do the fetch
           //.bodytomono what ever the body you get convert to the instance of the class
            //.block wait till the thing finish


           return new CatalogItem( movie.getName(), "released on 2005", rating.getRating());
       }).collect( Collectors.toList());

    }
}
