package com.ironyard.controller.mvc;

import com.ironyard.data.IronUser;
import com.ironyard.data.Permission;
import com.ironyard.repo.IronUserRepository;
import com.ironyard.repo.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jasonskipper on 11/2/16.
 */
@Controller
@RequestMapping(path = "/mvc/secure/admin")
public class MvcAdminController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IronUserRepository userRepository;


    @Autowired
    private PermissionRepository permissionRepository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String list(Model model) {
        String destination = "/secure/admin_user";
        addUserAndPermList(model);
        return destination;
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id,
                       Model model) {
        String destination = "/secure/admin_user";
        IronUser editMe = userRepository.findOne(id);
        // add a hash of perms this user case (so we can mark them in the checkboxes)
        HashMap<String, String> permsForThisUser = new HashMap<>();
        for(Permission p: editMe.getAbilities()){
            permsForThisUser.put(p.getKey(), p.getKey());
        }
        model.addAttribute("edit_user_perms", permsForThisUser);
        model.addAttribute("username", editMe.getUsername());
        model.addAttribute("displayname", editMe.getDisplayName());
        model.addAttribute("password", editMe.getPassword());
        model.addAttribute("password2", editMe.getPassword());
        model.addAttribute("id", editMe.getId());
        addUserAndPermList(model);

        return destination;
    }

    @RequestMapping(value = "user/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, Model model, HttpServletRequest req) {
        String destination = "/secure/admin_user";

        // make sure user not trying to delete currently logged in user
        IronUser usr = (IronUser)req.getSession().getAttribute("user");
        if(usr.getId() == id){
            model.addAttribute("error_message", "Can delete currently logged in user!");
        }else{
            // just delete
            userRepository.delete(id);
        }
        addUserAndPermList(model);
        return destination;
    }

    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam("id") Long id,
                            @RequestParam("username") String username,
                            @RequestParam("displayname") String displayname,
                            @RequestParam("password") String password,
                            @RequestParam("password2") String password2,
                            @RequestParam(value = "permissions", required = false ) Long[] perms,
                            Model model) {

        String destination = "redirect:/mvc/secure/admin/users";
        // check password match?
        if (!password.equals(password2)) {
            // handle error?
            model.addAttribute("error_message", "Passwords do not match!");
            addUserAndPermList(model);
            destination = "/secure/admin_user";
            // keep a couple fields to be nice to user
            model.addAttribute("username", username);
            model.addAttribute("displayname", displayname);
            model.addAttribute("id", id);

        } else {
            if(id == null) {
                // create user
                IronUser myNewUser = new IronUser();
                myNewUser.setUsername(username);
                myNewUser.setDisplayName(displayname);
                myNewUser.setPassword(password);

                if (perms != null) {
                    myNewUser.setAbilities(new HashSet<>());
                    // fetch perms
                    for (int i = 0; i < perms.length; i++) {
                        myNewUser.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(myNewUser);
            }else {

                // edit user
                IronUser editMe = userRepository.findOne(id);
                editMe.setUsername(username);
                editMe.setDisplayName(displayname);
                editMe.setPassword(password);
                // clear out any existing perms
                if(editMe.getAbilities() != null){
                    editMe.getAbilities().clear();
                }else{
                    editMe.setAbilities(new HashSet<>());
                }

                // add any selcted
                if (perms != null && perms.length > 0) {
                    // fetch perms and add
                    for (int i = 0; i < perms.length; i++) {
                        editMe.getAbilities().add(permissionRepository.findOne(perms[i]));
                    }
                }
                userRepository.save(editMe);
            }

        }

        return destination;
    }


    private void addUserAndPermList(Model model){
        Iterable<IronUser> users = userRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Permission> permissions = permissionRepository.findAll();
        model.addAttribute("permissions", permissions);
    }
}
