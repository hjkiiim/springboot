package com.example.websocket.controller

import com.example.websocket.service.MainService
import jakarta.annotation.Resource
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController
class MainController {

    @Resource(name = "MainService")
    lateinit var service: MainService

    @GetMapping(value = ["/"])
    fun home(modelAndView: ModelAndView):ModelAndView{
        modelAndView.addObject("", "")
        modelAndView.viewName = "index"
        return modelAndView
    }

    @PostMapping("/websocket")
    fun sendMessage(@RequestBody message: String){
        service.addEvent(message)
    }
}