package com.ironyard.controller.rest;

import com.ironyard.data.IronUser;
import com.ironyard.data.IronUser;
import com.ironyard.repo.IronUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jasonskipper on 11/1/16.
 */
@RestController
@RequestMapping(path = "/rest/user")
public class IronUserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IronUserRepository ironUserRepository;


    /**
     * Save the provided IronUser
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public IronUser save(@RequestBody IronUser aUser) {
        log.debug("Begin save:" + aUser);
        ironUserRepository.save(aUser);
        IronUser found = ironUserRepository.findOne(aUser.getId());
        log.debug("End save:" + aUser);
        return found;
    }

    /**
     * Update the IronUser
     * @param aUser
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public IronUser update(@RequestBody IronUser aUser) {
        log.debug("Begin update:" + aUser);
        ironUserRepository.save(aUser);
        IronUser found = ironUserRepository.findOne(aUser.getId());
        log.debug("End update:" + found);
        return found;
    }

    /**
     * Get the IronUser by id and return it
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public IronUser show(@PathVariable Long id) {
        log.debug("Begin show:" + id);
        IronUser found = ironUserRepository.findOne(id);
        log.debug("End show:" + found);
        return found;

    }


    /**
     * List the IronUsers matching the request
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<IronUser> listAll(@RequestParam("page") Integer page,
                                      @RequestParam("size") Integer size,
                                      @RequestParam(value = "sortby", required = false) String sortby,
                                      @RequestParam(value = "dir", required = false) Sort.Direction direction) {

        log.debug(String.format("Begin listAll (page:%s, size:%s, sortby:%s, dir:%s):",page,size,sortby,direction));

        // DEFAULT Sort property
        if (sortby == null) {
            sortby = "displayName";
        }

        // DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        Iterable<IronUser> found =  ironUserRepository.findAll(pr);
        log.debug(String.format("End listAll: %s", found));

        return found;
    }

    /**
     * Delete the specified IronUser
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public IronUser delete(@PathVariable Long id) {
        log.debug(String.format("Begin delete: %s", id));
        IronUser deleted = ironUserRepository.findOne(id);
        ironUserRepository.delete(id);
        log.debug(String.format("End delete: %s", deleted));
        return deleted;
    }

    /**
     * Catch any errors from this contorller
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e) {
        log.error("Error in IronUserController", e);
        return "Something went wrong :/";
    }
}
