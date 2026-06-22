package com.project.back_end.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.back_end.services.TokenService;

import javax.servlet.http.HttpServletRequest;

@Controller("/dashboard")
@RequestMapping
public class DashboardController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/adminDashboard/{token}")
    public ModelAndView adminDashboard(@PathVariable("token") String token) {
        // Validate token for admin role
        if (tokenValidationService.isValidToken(token, "admin")) {
            // Forward to admin dashboard view
            return new ModelAndView("admin/adminDashboard");
        }
        // Redirect to root URL if invalid
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/doctorDashboard/{token}")
    public ModelAndView doctorDashboard(@PathVariable("token") String token) {
        if (tokenValidationService.isValidToken(token, "doctor")) {
            return new ModelAndView("doctor/doctorDashboard");
        }
        return new ModelAndView("redirect:/");
    }

}
