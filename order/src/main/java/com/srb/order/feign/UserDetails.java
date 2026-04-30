package com.srb.order.feign;

import com.srb.order.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("AUTH-SERVICE")
public interface UserDetails {

    @GetMapping("/auth/get/{phNumber}")
    public UserDto getUserDetails(@PathVariable String phNumber);

}
