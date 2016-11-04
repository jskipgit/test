package com.ironyard.controller.mvc;

import com.ironyard.data.IronUser;
import com.ironyard.data.Movie;
import com.ironyard.dto.IronPager;
import com.ironyard.repo.IronUserRepository;
import com.ironyard.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by jasonskipper on 11/1/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/movie")
public class MvcMovieController {

    @Autowired
    IronUserRepository userRepository = null;

    @Autowired
    MovieRepository movieRepository = null;

    @RequestMapping(value = "favs", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request){
        // get current logged in user, need to case (IronUser) to proper type
        IronUser user = (IronUser)request.getSession().getAttribute("user");

        Long usrId = user.getId();

        // get users favorites
        Set<Movie> favs = userRepository.findOne(usrId).getFavs();


        // put them in a model
        model.addAttribute("favs", favs);

        // send them to the home page
        return "/secure/home";
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String allMovies(@RequestParam(value = "page", required = false) Integer page,
                            Model model, HttpServletRequest request){

        if(page == null){
            page = 0;
        }
        Sort s = new Sort(Sort.Direction.DESC, "title");
        PageRequest pr = new PageRequest(page, 2, s);
        Page<Movie> aPageOfMovies =  movieRepository.findAll(pr);

        IronPager mp = new IronPager(page ,aPageOfMovies);

        // put them in a model
        model.addAttribute("all_movies", aPageOfMovies.iterator());
        model.addAttribute("movie_pager", mp);

        // send them to the home page
        return "/secure/list_all_movies";
    }
}
