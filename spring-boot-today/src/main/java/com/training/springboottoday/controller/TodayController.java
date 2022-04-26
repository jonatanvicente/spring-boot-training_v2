package com.training.springboottoday.controller;

import com.training.springboottoday.dto.TodayJsonDto;
import com.training.springboottoday.dto.TodayPattern;
import com.training.springboottoday.service.TodayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Api(value = "Spring Boot Today API", tags = {"Today"})
@RestController
@RequestMapping(value = "/springboottraining/api/v1/today")
public class TodayController {

    private TodayService todayService;

    @Autowired
    public TodayController(TodayService userService){
        this.todayService = userService;
    }

    @ApiOperation(value = "You can call this to get date", tags = "Get Date")
    @GetMapping("/today")
    public String getToday(){
        return todayService.getTodaySimple();
    }

    @ApiOperation(value = "You can call this to get today's date in JSON format", tags = "Get Today's Date")
    @GetMapping("/todayJson")
    public Mono<?> getTodayJson(){

        return todayService.getTodayObject();
    }

    @ApiOperation(value = "You can call this to get today's date in JSON format, but delayed...", tags = "Get Today's Date")
    @GetMapping("/todayJsonDelayed")
    public Mono<?> getTodayJsonDelayed() {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        return todayService.getTodayObject();
    }

    /**
     * Input Format example:
     *      {
     *     "pattern": "dd/MM/yyyy HH:mm:ss"
     *      }
     * @param pattern
     * @return
     */
    @ApiOperation(value = "You can call this to get today's date in JSON format", tags = "Get Today's Date")
    @GetMapping("/todayJsonParameterized")
    public Mono<?> getTodayJsonWithParam(@RequestBody TodayPattern pattern){
        return todayService.getTodayParameterized(pattern);
    }


}
